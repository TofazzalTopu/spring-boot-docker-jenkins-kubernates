package com.info.demo.service.ria;

import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.model.ria.*;

import java.util.Date;
import java.util.List;

public interface RiaExchangeHouseApiService {
	SearchApiResponse searchRemittance(Long apiTraceId, SearchApiRequest searchApiRequest);

	PaymentApiResponse payRemittance(Long apiTraceId, PaymentApiRequest paymentApiRequest);

	List<RemittanceData> getDownloadableRemittance(RiaExchangePropertyDTO riaProperties, Date businessDate);

	boolean cancelRemittance(ApiTrace apiTrace);

	List<StopRemittanceData> getCancelRemittance(RiaExchangePropertyDTO riaProperties);

	StopRemittanceData notifyCancelStatus(StopRemittanceData data, RiaExchangePropertyDTO riaProperties);
	List<StopRemittanceData> notifyCancelStatus(RiaExchangePropertyDTO riaProperties);

	RemittanceData notifyRemittanceStatus(RemittanceData data, RiaExchangePropertyDTO riaProperties);
	List<RemittanceData> notifyRemittanceStatus(RiaExchangePropertyDTO riaProperties);

	void notifyRemittanceReceivedStatus(List<RemittanceData> data, RiaExchangePropertyDTO riaProperties, Date businessDate, boolean notifyReceived);

}