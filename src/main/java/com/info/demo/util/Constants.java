package com.info.demo.util;

public class Constants {
	public static final String API_STATUS_VALID = "VALID";
	public static final String API_STATUS_INVALID = "INVALID";
	public static final String API_STATUS_ERROR = "ERROR";
	public static final String API_STATUS_ERROR_TIMEOUT = "ERROR_TIMEOUT";
	public static final String PAYOUT_STATUS_CANCEL = "7000";

	public static final String CANCEL_SETTLED = "SETTLED";
	public static final String CANCEL_UNSETTLED = "UNSETTLED";
	public static final int TRY_COUNT = 5;
	public static final int CANCEL_THREAD_EXECUTOR_SIZE = 2;

	public static final int MIDDLEWARE_PUSH_UNDONE = 0;
	public static final int MIDDLEWARE_PUSH_DONE = 1;
	public static final int MIDDLEWARE_PUSH_ERROR = 2;

	public static final String API_SOURCE_TYPE = "API_PULL";

	public static final String[] BENE_CUST_RELATIONSHIP = new String[]{ "Father", "Mother", "Brother", "Sister", "Uncle", "Aunt","Aunty", "Husband", "Wife", "Daughter", "Son" };

	public static final String REFERENCE_NO_ALREADY_EXIST = "Reference no already exist!";
	public static final String REFERENCE_NOT_EXIST = "Reference not exist!";
	public static final String FILE_NAME = "RIA_API_DATA";


	public static final String RIA_API_DATA = "RIA API DATA";
	public static final String REQUEST_TYPE_SEARCH = "SEARCH";
	public static final String REQUEST_TYPE_PAYMENT = "PAYMENT";
	public static final String REQUEST_TYPE_CANCEL_REQ = "CANCEL_REQ";
	public static final String REQUEST_TYPE_CANCEL_REQ_RES = "CANCEL_REQ_RES";
	public static final String REQUEST_TYPE_DOWNLOAD_REQ = "DOWNLOAD_REQ";
	public static final String REQUEST_TYPE_NOTIFY_REM_STATUS = "NOTIFY_REM_STATUS";
	public static final String RIA_EXCHANGE_HOUSE = "RIA_EXCHANGE_HOUSE";

	public static final String RIA_DUPLICATE_TRANSACTION_NOTIFY_MESSAGE = "1007 - Paying Agent / Provider considers that an identical transaction had been sent or processed very recently.";


	public static final String REQUEST_TYPE_IC_FETCH_OUTSTANDING_TRANSACTION = "IC_FETCH_OUTSTANDING_TRANSACTION";
	public static final String REQUEST_TYPE_IC_UNLOCK_TRANSACTION = "IC_UNLOCK_TRANSACTION";
	public static final String REQUEST_TYPE_IC_CONFIRM_TRANSACTION = "IC_CONFIRM_TRANSACTION";
	public static final String REQUEST_TYPE_IC_RETRIEVE_PAYMENT_STATUS_TRANSACTION = "IC_RETRIEVE_PAYMENT_STATUS_TRANSACTION";
	public static final String REQUEST_TYPE_IC_RECEIVE_PAYMENT_TRANSACTION = "IC_RECEIVE_PAYMENT_TRANSACTION";

	public static final String EXCHANGE_HOUSE_INSTANT_CASH = "INSTANT_CASH";


    public static final String API_TRACE_CREATION_ERROR = "ApiTrace creation error.";
    public static final String TRACING_REMOVED_BECAUSE_NO_RECORD_FOUND_TRACE_ID ="{}, Tracing removed because no record found, TraceID: {} ";
    public static final String EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_OUTSTANDING_REMITTANCE = "ExchangeHouseProperty not exist for ICOutstandingRemittance!";
    public static final String EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_NOTIFY_STATUS = "ExchangeHouseProperty not exist for notifyPaymentStatus!";
    public static final String EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_UNLOCK_REMITTANCE = "ExchangeHouseProperty not exist for unlockICOutstandingRemittance!";
    public static final String EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_RECEIVE_PAYMENT = "ExchangeHouseProperty not exist for receivePayment!";
    public static final String EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_RETRIEVE_PAYMENT_STATUS = "ExchangeHouseProperty not exist for retrievePaymentStatus!";

	public static final String PAYMENT_RECEIVE_SUCCESSFULLY = "Instant Cash Payment Receive Successfully.";
	public static final String STATUS_RETRIEVE_SUCCESSFULLY = "Instant Cash Payment Status Retrieve Successfully.";
	public static final String PAYMENT_UNLOCK_SUCCESSFULLY = "Instant Cash Payment Unlock Successfully.";


	public static final String WRONG_ROUTING_OR_WRONG_BRANCH = "Wrong Routing Number or Wrong branch!";
	public static final String ACCOUNT_NAME_MISMATCH = "Account name mismatch!";
	public static final String ACCOUNT_NAME_IS_EMPTY = "Account name is empty!";
	public static final String ACCOUNT_NUMBER_INVALID = "Account number invalid!";
	public static final String ACCOUNT_NAME_IS_EMPTY_OR_NOT_VALID = "1047 - Details such as ID, name, address, relationship, date of birth from the beneficiary are missing or not valid.";
	public static final String BENEFICIARY_ACCOUNT_IS_NOT_CORRECT = "1017 - Beneficiary account is not correct or cannot be located.";
	public static final String BENEFICIARY_ACCOUNT_IS_NOT_ACTIVE_OR_CLOSED = "1020 - The account of the Beneficiary is closed, inactive or blocked, therefore funds cannot be delivered.";
	public static final String BENEFICIARY_NAME_DOES_NOT_MATCH = "1037 - Beneficiary name does not match account name.";
	public static final String INCORRECT_ROUTING_OR_WRONG_BRANCH = "1040 - The identification code of the scheme (i.e. service level or local instrument) specified in the message is incorrect.";
	public static final String ORDER_CAN_NOT_BE_PAID = "1034 - Order cannot be paid";

}
