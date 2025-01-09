package com.info.demo.model.ria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiaExchangePropertyDTO {

    private String exchangeCode;
    private String agentId;
    private String ocpApimSubKey;
    private String apiVersion;
    private String downloadableUrl;
    private String searchUrl;
    private String paymentUrl;
    private String cashPickUpCancelUrl;
    private String notifyRemStatusUrl;
    private String notifyCancelReqUrl;

}
