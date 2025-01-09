package com.info.demo.controller;

import com.info.demo.model.ApiResponse;
import com.info.demo.service.common.CommonService;
import com.info.demo.service.instantCash.ICPaymentReceiveService;
import com.info.demo.service.instantCash.ICRetrievePaymentStatusService;
import com.info.demo.service.instantCash.ICUnlockRemittanceService;
import com.info.demo.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.info.demo.util.Constants.*;

@CrossOrigin
@RestController
@RequestMapping("/instantCash")
public class InstantCashController {

    @Autowired
    private ICPaymentReceiveService icPaymentReceiveService;

    @Autowired
    private ICRetrievePaymentStatusService icRetrievePaymentStatusService;
    @Autowired
    private ICUnlockRemittanceService icUnlockRemittanceService;

    @Autowired
    private CommonService commonService;

    @GetMapping(value = "/receive-payment")
    public ResponseEntity<ApiResponse> receivePayment(@RequestHeader @NotNull String userId, @RequestHeader @NotNull String password, @RequestParam @NotNull String reference) {
        commonService.verifyAuthorization(userId, password);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), PAYMENT_RECEIVE_SUCCESSFULLY, icPaymentReceiveService.paymentReceive(ApiUtil.getICExchangeProperties(), reference)));
    }

    @GetMapping(value = "/status")
    public ResponseEntity<ApiResponse> retrievePaymentStatus(@RequestHeader @NotNull String userId, @RequestHeader @NotNull String password, @RequestParam @NotNull String reference) {
        commonService.verifyAuthorization(userId, password);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), STATUS_RETRIEVE_SUCCESSFULLY, icRetrievePaymentStatusService.retrievePaymentStatus(reference, ApiUtil.getICExchangeProperties())));
    }

    @PostMapping(value = "/unlock")
    public ResponseEntity<ApiResponse> unlockRemittance(@RequestHeader @NotNull String userId, @RequestHeader @NotNull String password, @RequestBody @NotNull String reference) {
        commonService.verifyAuthorization(userId, password);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), PAYMENT_UNLOCK_SUCCESSFULLY, icUnlockRemittanceService.unlockICOutstandingRemittance(reference, ApiUtil.getICExchangeProperties())));
    }

}
