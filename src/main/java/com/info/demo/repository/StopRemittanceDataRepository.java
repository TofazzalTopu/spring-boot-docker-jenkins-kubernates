package com.info.demo.repository;

import com.info.demo.entity.StopRemittanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StopRemittanceDataRepository extends JpaRepository<StopRemittanceData, Long> {
	
}
