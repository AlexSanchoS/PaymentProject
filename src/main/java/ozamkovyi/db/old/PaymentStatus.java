package ozamkovyi.db.old;

import ozamkovyi.db.entity.Payment;

public enum PaymentStatus {
    SENT, PREPARED;

    public static PaymentStatus getPaymentStatus(Payment payment){
        int PaymentStatusId = payment.getPaymentStatusId();
        return PaymentStatus.values()[PaymentStatusId];
    }

    public String getName(){ return name().toLowerCase();}
}
