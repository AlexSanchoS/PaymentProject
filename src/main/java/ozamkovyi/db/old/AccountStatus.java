package ozamkovyi.db.old;

import ozamkovyi.db.entity.BankAccount;

public enum  AccountStatus {
    BLOCKED, UNBLOCKED;

    public static AccountStatus getAccountStatus(BankAccount bankAccount){
        int accountStatusId = bankAccount.getAccountStatusId();
        return AccountStatus.values()[accountStatusId];
    }

    public String getName(){ return name().toLowerCase();}
}
