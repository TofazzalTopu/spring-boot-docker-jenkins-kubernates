package com.info.demo.repository;

import com.info.demo.entity.RemittanceProcessMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface RemittanceProcessMasterRepository extends JpaRepository<RemittanceProcessMaster, Long> {

    RemittanceProcessMaster findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatusOrderByIdDesc(Date date, String exchangeCode, int api_data, String processStatus);

}
