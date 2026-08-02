package com.info.demo.service.ria;

import com.info.demo.model.ria.PaymentApiRequest;
import com.info.demo.model.ria.PaymentApiResponse;
import com.info.demo.model.ria.SearchApiRequest;
import com.info.demo.model.ria.SearchApiResponse;

public interface RiaCashPaymentService {
	SearchApiResponse searchRemittance(SearchApiRequest searchApiRequest);

	PaymentApiResponse payRemittance(PaymentApiRequest paymentApiRequest);

}
