package com.info.demo.service.instantCash;

import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.model.ria.PaymentApiResponse;

import java.util.List;

public interface ICNotifyPaymentStatusRemittanceService {


   List<RemittanceData> notifyPaymentStatus(ICExchangePropertyDTO icExchangePropertyDTO);

    PaymentApiResponse notifyCashPaymentStatus(PaymentApiResponse paymentApiResponse, ICExchangePropertyDTO icDTO, String referenceNo);
}
