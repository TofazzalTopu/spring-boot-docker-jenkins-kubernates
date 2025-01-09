package com.info.demo.repository;

import com.info.demo.config.MCbsApiChannel;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.RemittanceProcessMaster;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Component
@Transactional
public class CommonRepository {
    private static final Logger logger = LoggerFactory.getLogger(CommonRepository.class);
    @PersistenceContext
    private EntityManager em;

    public Map<String, String> getExchangeHouseProp(String exchangeCode) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Query query;
            String sql = "select KEY_LABEL, KEY_VALUE FROM REM_EXC_HOUSE_PROP  WHERE EXCHANGE_CODE = ?";
            query = em.createNativeQuery(sql);
            query.setParameter(1, exchangeCode);
            @SuppressWarnings("unchecked")
            List<Object[]> list = (List<Object[]>) query.getResultList();
            if (list != null) {
                for (Object[] obj : list) {
                    if (obj[0] != null && obj[1] != null)
                        map.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }


    public Map<String, String> getExchangeHouseDownloadableKey(String propertyLabel) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Query query;
            String sql = "select EXCHANGE_CODE, KEY_LABEL FROM REM_EXC_HOUSE_PROP  WHERE KEY_LABEL LIKE ?";
            query = em.createNativeQuery(sql);
            query.setParameter(1, propertyLabel + "%");
            @SuppressWarnings("unchecked")
            List<Object[]> list = (List<Object[]>) query.getResultList();
            if (list != null) {
                for (Object[] obj : list) {
                    if (obj[0] != null && obj[1] != null)
                        map.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }


    public String getExchangeHousePropValue(String exchangeCode, String label) {
        try {
            Query query;
            String sql = "SELECT KEY_VALUE FROM REM_EXC_HOUSE_PROP  WHERE EXCHANGE_CODE = ? AND KEY_LABEL = ?";
            query = em.createNativeQuery(sql);
            query.setParameter(1, exchangeCode);
            query.setParameter(2, label);
            List resultList = query.getResultList();
            if (resultList != null && resultList.size() > 0) {
                return (String) resultList.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }


    public RemittanceProcessMaster getRemitProcMasterByApiData(Date date, String exchangeCode, int api_data,
                                                               String status) {
        RemittanceProcessMaster master = null;
        try {
            logger.info("Entered in getRemitProcMasterByApiData()");
            TypedQuery<RemittanceProcessMaster> query = em.createQuery(
                    "SELECT r FROM RemittanceProcessMaster r  where r.processDate = :process_date and r.exchangeHouseCode = :exchange_code and r.apiData = :api_data and r.processStatus = :status order by r.id desc ",
                    RemittanceProcessMaster.class);
            query.setParameter("process_date", date);
            query.setParameter("exchange_code", exchangeCode);
            query.setParameter("api_data", api_data);
            query.setParameter("status", status);
            query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            List<RemittanceProcessMaster> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                master = resultList.get(0);
            }
            return master;
        } catch (Exception ex) {
            logger.error("Error in getRemitProcMasterByApiData()", ex);
            return master;
        }
    }


    public List<RemittanceData> getRemittancebyReference(String referenceNo, String exchangeCode) {
        try {
            TypedQuery<RemittanceData> query = em.createQuery("SELECT r FROM RemittanceData r  where r.referenceNo = :reference_no and r.exchangeCode= :exchange_code ", RemittanceData.class);
            query.setParameter("reference_no", referenceNo);
            query.setParameter("exchange_code", exchangeCode);
            query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("Error in getRemittancebyReference()", ex);
            return null;
        }
    }


    public List<StopRemittanceData> getCancelRequestUnprocessData() {
        try {
            TypedQuery<StopRemittanceData> query = em.createQuery("SELECT r FROM StopRemittanceData r  where r.processStatus = 'PENDING'", StopRemittanceData.class);
            query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("Error in getCancelRequestUnprocessData()", ex);
            return new ArrayList<>();
        }
    }


    public List<RemittanceData> getRemittanceDataForNotification(String exchangeCode) {
        try {
            TypedQuery<RemittanceData> query = em.createQuery("SELECT r FROM RemittanceData r  where r.exchangeCode=:exchangeCode and r.middlewarePush = 0 and r.sourceType=:source_type and r.finalStatus IN ('02', '03', '04', '05','07','08','14','15', '22') and r.processStatus IN ('COMPLETED','REJECTED') and r.remittanceMessageType <> 'WEB' ", RemittanceData.class);
            query.setParameter("exchangeCode", exchangeCode);
            query.setParameter("source_type", Constants.API_SOURCE_TYPE);
            query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error("Error in getCancelRequestUnprocessData()", ex);
            return new ArrayList<>();
        }
    }


    public MCbsApiChannel getCBSLocalUser(String channel, String user) {
        MCbsApiChannel local = new MCbsApiChannel();
        try {
            Query query;
            String sql = "select CBSAPI_USERID, IP_ADDRESS, CBSAPI_USER_PASSWORD FROM RMS_API_CHANNELS  WHERE CBSAPI_CHANNEL = ? AND CBSAPI_USERID = 	?";
            query = em.createNativeQuery(sql);
            query.setParameter(1, channel);
            query.setParameter(2, user);
            Object[] obj = (Object[]) query.getSingleResult();
            if (obj != null) {
                local.setRequestUserId(String.valueOf(obj[0]));
                local.setIpAddress(String.valueOf(obj[1]));
                local.setRequestUserId(String.valueOf(obj[2]));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return local;
    }

    public boolean isRemittanceRefNoExist(String refNo, Date refDate) {
        try {
            TypedQuery<RemittanceData> query = em.createQuery("SELECT r FROM RemittanceData r  where r.referenceNo = :reference_no and r.referenceDate= :reference_date ", RemittanceData.class);
            query.setParameter("reference_no", refNo);
            query.setParameter("reference_date", refDate);
            query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            return !query.getResultList().isEmpty();
        } catch (Exception ex) {
            logger.error("Error in isRemittanceRefNoExist()", ex);
            return false;
        }

    }

    public boolean isUserExistByUserIdAndPassword(String userId, String password) {
        boolean result = false;
        try {
            Query query;
            String sql = "select count(*) FROM RMS_API_CHANNELS  WHERE CBSAPI_USERID	= ? AND CBSAPI_USER_PASSWORD = ?";
            query = em.createNativeQuery(sql);
            query.setParameter(1, userId);
            query.setParameter(2, password);

            BigDecimal rowCount = (BigDecimal) query.getSingleResult();

            if (rowCount.compareTo(BigDecimal.ZERO) > 0) {
                result = true;
            }

        } catch (Exception ex) {
            logger.error("Error in isUserExistByUserIdAndPassword()", ex);
        }
        return result;
    }
  public boolean loadBulkLoadData(String userId, String password) {
        boolean result = false;
        try {

//                Query query = sessionFactory.getCurrentSession().createSQLQuery("LOAD DATA INFILE :filename INTO TABLE TestTable FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (txn_amount,card_number,terminal_id)").setString("filename", "E:/amol.csv");
            Query query;
            String sql = "LOAD DATA INFILE :filename INTO TABLE TestTable FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (txn_amount,card_number,terminal_id)";
//            String sql = "select count(*) FROM RMS_API_CHANNELS  WHERE CBSAPI_USERID	= ? AND CBSAPI_USER_PASSWORD = ?";
            query = em.createQuery(sql);
            query.setParameter(1, userId);
            query.setParameter(2, password);

            BigDecimal rowCount = (BigDecimal) query.getSingleResult();

            if (rowCount.compareTo(BigDecimal.ZERO) > 0) {
                result = true;
            }

        } catch (Exception ex) {
            logger.error("Error in isUserExistByUserIdAndPassword()", ex);
        }
        return result;
    }

    public Map<String, String> getAccountBranchInfo(String accountNo) {
        logger.info("Enter into getAccountBranchInfo" + accountNo);
        Map<String, String> map = new HashMap<String, String>();
        try {
            Query query;
            String sql = "SELECT M.MBRN_CODE,M.MBRN_NAME,M.MBRN_MICR_CODE FROM IACLINK I,MBRN M WHERE I.IACLINK_ACTUAL_ACNUM = ? AND M.MBRN_CODE = I.IACLINK_BRN_CODE";
            query = em.createNativeQuery(sql);
            query.setParameter(1, accountNo);

            Object[] obj = (Object[]) query.getSingleResult();
            if (obj != null) {
                map.put("branch_code", String.valueOf(obj[0]));
                map.put("branch_name", String.valueOf(obj[1]));
                map.put("routing_no", String.valueOf(obj[2]));
            }
        } catch (Exception ex) {
            logger.error("Error in getAccountBranchInfo()", ex);
            map.put("branch_code", "0");
            map.put("branch_name", "");
            map.put("routing_no", "0");
        }

        return map;
    }

    public Timestamp getCurrentBusinessDate() {
        try {
            Query query;
            String qry = "SELECT PKG_PB_GLOBAL.FN_GET_CURR_BUS_DATE(1) FROM DUAL";
            query = em.createNativeQuery(qry);
            Timestamp timestamp = (Timestamp) query.getSingleResult();
            return timestamp;
        } catch (Exception ex) {
            logger.error("Error in getCurrentBusinessDate()", ex);
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }

}
