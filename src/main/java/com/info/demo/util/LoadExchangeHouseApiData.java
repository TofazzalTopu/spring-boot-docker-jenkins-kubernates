package com.info.demo.util;

import com.info.demo.model.ria.ExchangeHouseApiData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoadExchangeHouseApiData {
	private static final Logger logger = LoggerFactory.getLogger(LoadExchangeHouseApiData.class);
	
	public Map<String,ExchangeHouseApiData> exchangeHouseApiDataMapList = new HashMap<String, ExchangeHouseApiData>();
	
	@PostConstruct
	void postConstruct() {
		logger.info("Enter into LoadExchangeHouseApiData@PostConstruct");
		
		// TODO: At this moment ExchangeHouseApiData is hard coded but in future it will come from database table
		ExchangeHouseApiData exchangeHouseApiData = new ExchangeHouseApiData();
		Map<String,String> exchangeHouseApiDataMap = new HashMap<String, String>();
		
		// Add Ria financial API service parameter
		exchangeHouseApiDataMap.put("search_url","https://stagingapi.rialink.net/PayOrders/Order/CashPickUp");
		exchangeHouseApiDataMap.put("payment_url","https://stagingapi.rialink.net/PayOrders/Order/CashPickUp/Payment");
		exchangeHouseApiDataMap.put("ria-AgentId", "12345");
		exchangeHouseApiDataMap.put("Ocp-Apim-Subscription-Key", "8bd2eb77a0664b9b846272e55d4d9ef2");
		exchangeHouseApiData.setExchangeHouseApiDataMap(exchangeHouseApiDataMap);
		exchangeHouseApiDataMapList.put("1452070046",exchangeHouseApiData);
		
		// Add Prabhu money parameter 
		exchangeHouseApiData = new ExchangeHouseApiData();
		exchangeHouseApiDataMap = new HashMap<String, String>();
		exchangeHouseApiDataMap.put("wsdl_url", "https://localhost:8443/ws/prabhumoney/exchangehouseapi.wsdl");
		exchangeHouseApiDataMap.put("marshaller_pkg", "com.info.demo.client.model.prabhumoney");
		exchangeHouseApiDataMap.put("user_id", "4321");
		exchangeHouseApiDataMap.put("password", "123");
		
		exchangeHouseApiData.setExchangeHouseApiDataMap(exchangeHouseApiDataMap);
		exchangeHouseApiDataMapList.put("1452070044",exchangeHouseApiData);
		
		logger.info("Exit from LoadExchangeHouseApiData@PostConstruct");
	}

	public ExchangeHouseApiData getExchangeHouseApiDataMap(String exchangeCode) {
		ExchangeHouseApiData exchangeHouseApiData = new ExchangeHouseApiData();
		
		if(exchangeHouseApiDataMapList != null) {
			exchangeHouseApiData = exchangeHouseApiDataMapList.get(exchangeCode);
		}
		return exchangeHouseApiData;
	}

}
