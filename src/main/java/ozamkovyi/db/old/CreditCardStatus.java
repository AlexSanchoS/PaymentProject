package ozamkovyi.db.old;

import ozamkovyi.db.entity.CreditCard;

public enum  CreditCardStatus {
    BLOCKED, UNBLOCKED;

    public static CreditCardStatus getCreditCardStatus(CreditCard creditCard){
        int cardStatusId = creditCard.getCardStatusId();
        return CreditCardStatus.values()[cardStatusId];
    }

    public String getName(){ return name().toLowerCase();}
}
