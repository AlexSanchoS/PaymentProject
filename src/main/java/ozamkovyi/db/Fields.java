package ozamkovyi.db;

public final class Fields {

    public static final String CLIENT__ID = "id";
    public static final String CLIENT__LOGIN = "login";
    public static final String CLIENT__PASSWORD = "password";
    public static final String CLIENT__NAME = "name";
    public static final String CLIENT__DATE_OF_BIRTH = "date_of_birth";
    public static final String CLIENT__LANGUAGE = "language";

    public static final String CARD_STATUS__BLOCKED = "blocked";
    public static final String CARD_STATUS__UNBLOCKED = "unlocked";


    public static final String ACCOUNT_STATUS__BLOCKED = "blocked";
    public static final String ACCOUNT_STATUS__UNBLOCKED = "unlocked";
    public static final String ACCOUNT_STATUS__EXPECTATION = "";



    public static final String ACCOUNT_STATUS__ID = "id";
    public static final String ACCOUNT_STATUS__STATUS = "status";

    public static final String CARD_STATUS__ID = "id";
    public static final String CARD_STATUS__STATUS = "status";

    public static final String PAYMENT_STATUS__ID = "id";
    public static final String PAYMENT_STATUS__STATUS = "status";

    public static final String ADMIN__ID = "id";
    public static final String ADMIN__LOGIN = "login";
    public static final String ADMIN__PASSWORD = "password";
    public static final String ADMIN__LANGUAGE = "language";

    public static final String BANK_ACCOUNT__NUMBER = "number";
    public static final String BANK_ACCOUNT__BALANCE = "balance";
    public static final String BANK_ACCOUNT__CURRENCY_ID = "currency_id";
    public static final String BANK_ACCOUNT__USER_ID = "user_id";
    public static final String BANK_ACCOUNT__ACCOUNT_STATUS_ID = "account_status_id";

    public static final String CREDIT_CARD__ID = "id";
    public static final String CREDIT_CARD__NUMBER = "number";
    public static final String CREDIT_CARD__VALIDITY = "validity";
    public static final String CREDIT_CARD__BANK_ACCOUNT_NUMBER = "bank_account_number";
    public static final String CREDIT_CARD__USER_ID = "user_id";
    public static final String CREDIT_CARD__CARD_STATUS_ID = "card_status_id";

    public static final String CURRENCY__ID = "id";
    public static final String CURRENCY__NAME = "name";
    public static final String CURRENCY__COURSE = "course";

    public static final String PAYMENT__ID = "id";
    public static final String PAYMENT__NUMBER = "number";
    public static final String PAYMENT__DATE = "date";
    public static final String PAYMENT__AMOUNT = "amount";
    public static final String PAYMENT__PAYMENT_STATUS_ID = "payment_status_id";
    public static final String PAYMENT__RECIPIENT_CREDIT_CARD = "recipient_credit_card";
    public static final String PAYMENT__SENDER_CREDIT_CARD = "sender_credit_card";


    public static final String TABLE__ACCOUNT_STATUS = "account_status";
    public static final String TABLE__ADMIN = "admin";
    public static final String TABLE__BANK_ACCOUNT = "bank_account";
    public static final String TABLE__CARD_STATUS = "card_status";
    public static final String TABLE__CLIENT = "client";
    public static final String TABLE__CREDIT_CARD = "credit_card";
    public static final String TABLE__CURRENCY = "currency";
    public static final String TABLE__PAYMENT = "payment";
    public static final String TABLE__PAYMENT_STATUS = "payment_status";

}
