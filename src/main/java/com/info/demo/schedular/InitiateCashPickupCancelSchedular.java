package com.info.demo.schedular;

import com.info.demo.entity.ApiTrace;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.ria.RiaExchangeHouseApiService;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class InitiateCashPickupCancelSchedular {
	private static final Logger logger = LoggerFactory.getLogger(InitiateCashPickupCancelSchedular.class);

	@Autowired
	private ApiTraceService apiTraceService;
	@Autowired
	private RiaExchangeHouseApiService riaExchangeHouseApiService;

	@Scheduled(fixedRate = 1000 * 60 * 5)
	public void scheduleFixedRateTask() {
		logger.info("InitiateCashPickupCancelSchedular: Schedular has been started to pull the cancellable data of cash pickup");
		List<String> tranNoListForUpdateRequired = searchAndInitiateCancel();

		// Need to update remcashpay payout status - 7000
		if (!tranNoListForUpdateRequired.isEmpty()) {
			updateRejectBatchStatus(tranNoListForUpdateRequired);
		}
		logger.info("InitiateCashPickupCancelSchedular completed the task");
	}

	private List<String> searchAndInitiateCancel() {
		final List<String> tranNoListForUpdateRequired = new ArrayList<String>();
		ExecutorService executor = Executors.newFixedThreadPool(Constants.CANCEL_THREAD_EXECUTOR_SIZE);
		try {
			List<Long> apiTraceList = apiTraceService.getCancelIds(Constants.TRY_COUNT);
			for (Long apiTraceId : apiTraceList) {
				Runnable process = new Runnable() {
					@Override
					public void run() {
						try {
							Optional<ApiTrace> apiTraceOptional = apiTraceService.findById(apiTraceId);
							if (apiTraceOptional.isPresent()) {

								ApiTrace apiTrace = apiTraceOptional.get();
								boolean isCancelDone = riaExchangeHouseApiService.cancelRemittance(apiTrace);
								if (isCancelDone) {
									apiTrace.setCancelStatus(Constants.CANCEL_SETTLED);
									tranNoListForUpdateRequired.add(apiTrace.getTranno());
								} else {
									apiTrace.setTryCount(apiTrace.getTryCount() + 1);
									apiTrace.setCancelStatus(Constants.CANCEL_UNSETTLED);
								}
								apiTraceService.save(apiTrace);
							}
						} catch (Exception ex) {
							logger.error("Error in searchAndInitiateCancel() thread. Error = " + ex);
						}
					}
				};
				executor.execute(process);
			}
		} catch (Exception e) {
			logger.error("Error in searchAndInitiateCancel(). Error = " + e);
		} finally {
			executor.shutdown();
			// Wait until all tasks are finished
			while (!executor.isTerminated()) {
			}
			logger.info("executor shutdown done......");
		}
		return tranNoListForUpdateRequired;
	}

	private void updateRejectBatchStatus(List<String> tranNoListForUpdateRequired) {
		logger.info("Enter into updateRejectBatchStatus:: tranNoListForUpdateRequired size = " + tranNoListForUpdateRequired.size());
		try {
			apiTraceService.updatePayoutStatus(Constants.PAYOUT_STATUS_CANCEL, tranNoListForUpdateRequired);
			logger.info("updatePayoutStatus success with code " + Constants.PAYOUT_STATUS_CANCEL);
		} catch (Exception e) {
			logger.error("Error in updateRejectBatchStatus(). Error = " + e);
		}
	}


}
