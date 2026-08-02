package com.info.demo.service.instantCash;

import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.model.ria.SearchApiResponse;

import javax.validation.constraints.NotNull;

public interface ICPaymentReceiveService {

    SearchApiResponse paymentReceive(@NotNull ICExchangePropertyDTO dto, @NotNull String referenceNo);

}
