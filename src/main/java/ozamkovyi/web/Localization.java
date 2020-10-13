package ozamkovyi.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Localization {


    private String loginLoginLabel = null;
    private String loginLoginButton = null;
    private String loginRegistrationButton = null;
    private String loginPasswordLabel = null;
    private String local = "en";
    private String registrationLoginLabel = null;
    private String registrationPasswordLabel = null;
    private String registrationNameLabel = null;
    private String registrationDateOfBirthLabel = null;
    private String registrationRegistrationButton = null;
    private String clientHomepageLogOutButton = null;
    private String clientHomepageMyCardsButton = null;
    private String clientHomepageMyAccountButton = null;
    private String clientHomepageMyPaymentButton = null;
    private String clientCardMenuButtonAddCard = null;
    private String clientCardMenuButtonLogOut = null;
    private String clientCardMenuTableNumber = null;
    private String clientCardMenuTableValidity = null;
    private String clientCardMenuTableCurrency = null;
    private String clientCardMenuTableBalance = null;
    private String clientCardMenuTableSort = null;
    private String clientCardMenuButtonBloc = null;
    private String clientCardMenuButtonUnBloc = null;
    private String clientCardMenuSortByCurrency = null;
    private String clientCardMenuSortByBalance = null;
    private String clientAccountMenuButtonLogOut = null;
    private String clientAccountMenuTableNumber = null;
    private String clientAccountMenuTableCurrency = null;
    private String clientAccountMenuTableBalance = null;
    private String clientAccountMenuButtonBloc = null;
    private String clientAccountMenuButtonUnBloc = null;
    private String clientAccountMenuButtonNewAccount = null;
    private String clientAccountMenuButtonReplenish = null;
    private String clientAccountMenuTableSort = null;
    private String clientAccountMenuSortByNumber = null;
    private String clientAccountMenuSortByCurrency = null;
    private String clientAccountMenuSortByBalance = null;
    private String clientAccountMenuTableAmount = null;

    private String clientPaymentMenuButtonNewPayment = null;
    private String clientPaymentMenuButtonLogOut = null;
    private String clientPaymentMenuTableNumber = null;
    private String clientPaymentMenuTableDate = null;
    private String clientPaymentMenuTableAmount = null;
    private String clientPaymentMenuTableSender = null;
    private String clientPaymentMenuTableRecipient = null;
    private String clientPaymentMenuTableRecipientName = null;
    private String clientPaymentMenuTableStatus = null;
    private String clientPaymentMenuButtonStatus = null;
    private String clientPaymentMenuButtonSortByNumber = null;
    private String clientPaymentMenuButtonSortByDate = null;
    private String clientPaymentMenuButtonSortByAmount = null;
    private String clientPaymentMenuButtonSortByStatus = null;
    private String clientPaymentMenuButtonRepeat = null;
    private String clientPaymentMenuButtonConfirm = null;

    private String unlockRequestsButtonLogOut = null;
    private String unlockRequestsButtonAllUsers = null;
    private String unlockRequestsButtonUnlockRequests = null;
    private String unlockRequestsTableNumber = null;
    private String unlockRequestsTableBalance = null;
    private String unlockRequestsTableCurrency = null;
    private String unlockRequestsTableName = null;
    private String unlockRequestsButtonUnlock = null;
    private String unlockRequestsButtonSortByName = null;
    private String unlockRequestsButtonSortByNumber = null;

    private String allUsersButtonLogOut = null;
    private String allUsersButtonAllUsers = null;
    private String allUsersButtonUnlockRequests = null;
    private String allUsersTableName = null;
    private String allUsersTableCountOfAccount = null;
    private String allUsersTableCountOfCard = null;
    private String allUsersButtonShowAllCard = null;
    private String allUsersButtonShowAllAccount = null;
    private String allUsersButtonUnblock = null;
    private String allUsersButtonBlock = null;
    private String allUsersButtonSortByName = null;
    private String allUsersButtonSortByStatus = null;

    private String wrongLogin = null;
    private String notAdult = null;

    public Localization() {
        local = "en";
        setProperties("C:\\Users\\User\\IdeaProjects\\PaymentProject\\src\\main\\java\\resources_en.properties");
    }


    public void setLocal(String local) {
        if (local.equals("ua")) {
            this.local = local;

            setProperties("C:\\Users\\User\\IdeaProjects\\PaymentProject\\src\\main\\java\\resources_ru.properties");
        } else {
            this.local = local;
            setProperties("C:\\Users\\User\\IdeaProjects\\PaymentProject\\src\\main\\java\\resources_en.properties");
        }
    }

    private void setProperties(String path) {
        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream(path)) {
            property.load(fis);

            loginLoginLabel = property.getProperty("login_jsp.label.login");
            loginPasswordLabel = property.getProperty("login_jsp.label.password");
            loginLoginButton = property.getProperty("login_jsp.button.login");
            loginRegistrationButton = property.getProperty("login_jsp.button.registration");
            wrongLogin = property.getProperty("login_jsp.wrongLogin");
            registrationLoginLabel = property.getProperty("registration_jsp.label.login");
            registrationPasswordLabel = property.getProperty("registration_jsp.label.password");
            registrationNameLabel = property.getProperty("registration_jsp.label.name");
            registrationDateOfBirthLabel = property.getProperty("registration_jsp.label.DateOfBirth");
            registrationRegistrationButton = property.getProperty("registration_jsp.button.registration");
            clientHomepageLogOutButton = property.getProperty("clientHomepage_jsp.button.logOut");
            clientHomepageMyCardsButton = property.getProperty("clientHomepage_jsp.button.myCards");
            clientHomepageMyAccountButton = property.getProperty("clientHomepage_jsp.button.myAccount");
            clientHomepageMyPaymentButton = property.getProperty("clientHomepage_jsp.button.myPayment");
            notAdult = property.getProperty("clientNotAdult");

            clientCardMenuButtonAddCard = property.getProperty("clientCardMenu_jsp.button.addCard");
            clientCardMenuButtonLogOut = property.getProperty("clientCardMenu_jsp.button.logOut");
            clientCardMenuTableNumber = property.getProperty("clientCardMenu_jsp.table.number");
            clientCardMenuTableValidity = property.getProperty("clientCardMenu_jsp.table.validity");
            clientCardMenuTableCurrency = property.getProperty("clientCardMenu_jsp.table.currency");
            clientCardMenuTableBalance = property.getProperty("clientCardMenu_jsp.table.balance");
            clientCardMenuTableSort = property.getProperty("clientCardMenu_jsp.label.sort");
            clientCardMenuButtonBloc = property.getProperty("clientCardMenu_jsp.button.bloc");
            clientCardMenuButtonUnBloc = property.getProperty("clientCardMenu_jsp.button.unblock");
            clientCardMenuSortByCurrency = property.getProperty("clientCardMenu_jsp.sort.byCurrency");
            clientCardMenuSortByBalance = property.getProperty("clientCardMenu_jsp.sort.byBalance");

            clientAccountMenuButtonLogOut = property.getProperty("clientAccountMenu_jsp.button.logOut");
            clientAccountMenuTableNumber = property.getProperty("clientAccountMenu_jsp.table.number");
            clientAccountMenuTableCurrency = property.getProperty("clientAccountMenu_jsp.table.currency");
            clientAccountMenuTableBalance = property.getProperty("clientAccountMenu_jsp.table.balance");
            clientAccountMenuButtonBloc = property.getProperty("clientAccountMenu_jsp.button.bloc");
            clientAccountMenuButtonUnBloc = property.getProperty("clientAccountMenu_jsp.button.unblock");
            clientAccountMenuButtonNewAccount = property.getProperty("clientAccountMenu_jsp.button.newAccount");
            clientAccountMenuButtonReplenish = property.getProperty("clientAccountMenu_jsp.button.replenish");
            clientAccountMenuTableSort = property.getProperty("clientAccountMenu_jsp.label.sort");
            clientAccountMenuSortByNumber = property.getProperty("clientAccountMenu_jsp.button.sort.byNumber");
            clientAccountMenuSortByCurrency = property.getProperty("clientAccountMenu_jsp.button.sort.byCurrency");
            clientAccountMenuSortByBalance = property.getProperty("clientAccountMenu_jsp.button.sort.byBalance");
            clientAccountMenuTableAmount = property.getProperty("clientAccountMenu_jsp.table.amount");

            clientPaymentMenuButtonNewPayment = property.getProperty("clientPaymentMenu_jsp.button.newPayment");
            clientPaymentMenuButtonLogOut = property.getProperty("clientPaymentMenu_jsp.button.logOut");
            clientPaymentMenuTableNumber = property.getProperty("clientPaymentMenu_jsp.table.number");
            clientPaymentMenuTableDate = property.getProperty("clientPaymentMenu_jsp.table.date");
            clientPaymentMenuTableAmount = property.getProperty("clientPaymentMenu_jsp.table.amount");
            clientPaymentMenuTableSender = property.getProperty("clientPaymentMenu_jsp.table.sender");
            clientPaymentMenuTableRecipient = property.getProperty("clientPaymentMenu_jsp.table.recipient");
            clientPaymentMenuTableRecipientName = property.getProperty("clientPaymentMenu_jsp.table.recipientName");
            clientPaymentMenuTableStatus = property.getProperty("clientPaymentMenu_jsp.table.status");
            clientPaymentMenuButtonStatus = property.getProperty("clientPaymentMenu_jsp.button.status");
            clientPaymentMenuButtonSortByNumber = property.getProperty("clientPaymentMenu_jsp.button.sortByNumber");
            clientPaymentMenuButtonSortByDate = property.getProperty("clientPaymentMenu_jsp.button.sortByDate");
            clientPaymentMenuButtonSortByAmount = property.getProperty("clientPaymentMenu_jsp.button.sortByAmount");
            clientPaymentMenuButtonSortByStatus = property.getProperty("clientPaymentMenu_jsp.button.sortByStatus");
            clientPaymentMenuButtonRepeat = property.getProperty("clientPaymentMenu_jsp.button.repeat");
            clientPaymentMenuButtonConfirm = property.getProperty("clientPaymentMenu_jsp.button.confirm");

            unlockRequestsButtonLogOut = property.getProperty("adminHomepage_jsp.button.logOut");
            unlockRequestsButtonAllUsers = property.getProperty("adminHomepage_jsp.button.allUsers");
            unlockRequestsButtonUnlockRequests = property.getProperty("adminHomepage_jsp.button.unlockRequests");
            unlockRequestsTableNumber = property.getProperty("adminHomepage_jsp.table.number");
            unlockRequestsTableBalance = property.getProperty("adminHomepage_jsp.table.balance");
            unlockRequestsTableCurrency = property.getProperty("adminHomepage_jsp.table.currency");
            unlockRequestsTableName = property.getProperty("adminHomepage_jsp.table.name");
            unlockRequestsButtonUnlock = property.getProperty("adminHomepage_jsp.button.unblock");
            unlockRequestsButtonSortByName = property.getProperty("adminHomepage_jsp.button.sortByName");
            unlockRequestsButtonSortByNumber = property.getProperty("adminHomepage_jsp.button.sortByNumber");

            allUsersButtonLogOut = property.getProperty("allUsers_jsp.button.logOut");
            allUsersButtonAllUsers = property.getProperty("allUsers_jsp.button.allUsers");
            allUsersButtonUnlockRequests = property.getProperty("allUsers_jsp.button.unlockRequests");
            allUsersTableName = property.getProperty("allUsers_jsp.table.name");
            allUsersTableCountOfAccount = property.getProperty("allUsers_jsp.table.countOfAccount");
            allUsersTableCountOfCard = property.getProperty("allUsers_jsp.table.countOfCard");
            allUsersButtonShowAllCard = property.getProperty("allUsers_jsp.button.showAllCard");
            allUsersButtonShowAllAccount = property.getProperty("allUsers_jsp.button.showAllAccount");
            allUsersButtonUnblock = property.getProperty("allUsers_jsp.button.unblock");
            allUsersButtonBlock = property.getProperty("allUsers_jsp.button.block");
            allUsersButtonSortByName = property.getProperty("allUsers_jsp.button.sortByName");
            allUsersButtonSortByStatus = property.getProperty("allUsers_jsp.button.sortByStatus");

        } catch (IOException e) {
            System.err.println("file not found");
        }
    }

    public String getLoginLoginLabel() {
        return loginLoginLabel;
    }

    public String getLoginLoginButton() {
        return loginLoginButton;
    }

    public String getLoginRegistrationButton() {
        return loginRegistrationButton;
    }

    public String getLoginPasswordLabel() {
        return loginPasswordLabel;
    }

    public String getWrongLogin() {
        return wrongLogin;
    }

    public String getRegistrationLoginLabel() {
        return registrationLoginLabel;
    }

    public String getRegistrationPasswordLabel() {
        return registrationPasswordLabel;
    }

    public String getRegistrationNameLabel() {
        return registrationNameLabel;
    }

    public String getRegistrationDateOfBirthLabel() {
        return registrationDateOfBirthLabel;
    }

    public String getRegistrationRegistrationButton() {
        return registrationRegistrationButton;
    }

    public String getLocal() {
        return local;
    }

    public String getClientHomepageLogOutButton() {
        return clientHomepageLogOutButton;
    }

    public String getClientHomepageMyCardsButton() {
        return clientHomepageMyCardsButton;
    }

    public String getClientHomepageMyAccountButton() {
        return clientHomepageMyAccountButton;
    }

    public String getClientHomepageMyPaymentButton() {
        return clientHomepageMyPaymentButton;
    }

    public String getNotAdult() {
        return notAdult;
    }

    public String getClientCardMenuButtonAddCard() {
        return clientCardMenuButtonAddCard;
    }

    public String getClientCardMenuButtonLogOut() {
        return clientCardMenuButtonLogOut;
    }

    public String getClientCardMenuTableNumber() {
        return clientCardMenuTableNumber;
    }

    public String getClientCardMenuTableValidity() {
        return clientCardMenuTableValidity;
    }

    public String getClientCardMenuTableCurrency() {
        return clientCardMenuTableCurrency;
    }

    public String getClientCardMenuTableBalance() {
        return clientCardMenuTableBalance;
    }

    public String getClientCardMenuTableSort() {
        return clientCardMenuTableSort;
    }

    public String getClientCardMenuButtonBloc() {
        return clientCardMenuButtonBloc;
    }

    public String getClientCardMenuButtonUnBloc() {
        return clientCardMenuButtonUnBloc;
    }

    public String getClientCardMenuSortByCurrency() {
        return clientCardMenuSortByCurrency;
    }

    public String getClientCardMenuSortByBalance() {
        return clientCardMenuSortByBalance;
    }

    public String getClientAccountMenuButtonLogOut() {
        return clientAccountMenuButtonLogOut;
    }

    public String getClientAccountMenuTableNumber() {
        return clientAccountMenuTableNumber;
    }

    public String getClientAccountMenuTableCurrency() {
        return clientAccountMenuTableCurrency;
    }

    public String getClientAccountMenuTableBalance() {
        return clientAccountMenuTableBalance;
    }

    public String getClientAccountMenuButtonBloc() {
        return clientAccountMenuButtonBloc;
    }

    public String getClientAccountMenuButtonUnBloc() {
        return clientAccountMenuButtonUnBloc;
    }

    public String getClientAccountMenuButtonNewAccount() {
        return clientAccountMenuButtonNewAccount;
    }

    public String getClientAccountMenuButtonReplenish() {
        return clientAccountMenuButtonReplenish;
    }

    public String getClientAccountMenuTableSort() {
        return clientAccountMenuTableSort;
    }

    public String getClientAccountMenuSortByNumber() {
        return clientAccountMenuSortByNumber;
    }

    public String getClientAccountMenuSortByCurrency() {
        return clientAccountMenuSortByCurrency;
    }

    public String getClientAccountMenuSortByBalance() {
        return clientAccountMenuSortByBalance;
    }

    public String getClientAccountMenuTableAmount() {
        return clientAccountMenuTableAmount;
    }

    public String getClientPaymentMenuButtonNewPayment() {
        return clientPaymentMenuButtonNewPayment;
    }

    public String getClientPaymentMenuButtonLogOut() {
        return clientPaymentMenuButtonLogOut;
    }

    public String getClientPaymentMenuTableNumber() {
        return clientPaymentMenuTableNumber;
    }

    public String getClientPaymentMenuTableDate() {
        return clientPaymentMenuTableDate;
    }

    public String getClientPaymentMenuTableAmount() {
        return clientPaymentMenuTableAmount;
    }

    public String getClientPaymentMenuTableSender() {
        return clientPaymentMenuTableSender;
    }

    public String getClientPaymentMenuTableRecipient() {
        return clientPaymentMenuTableRecipient;
    }

    public String getClientPaymentMenuTableRecipientName() {
        return clientPaymentMenuTableRecipientName;
    }

    public String getClientPaymentMenuTableStatus() {
        return clientPaymentMenuTableStatus;
    }

    public String getClientPaymentMenuButtonStatus() {
        return clientPaymentMenuButtonStatus;
    }

    public String getClientPaymentMenuButtonSortByNumber() {
        return clientPaymentMenuButtonSortByNumber;
    }

    public String getClientPaymentMenuButtonSortByDate() {
        return clientPaymentMenuButtonSortByDate;
    }

    public String getClientPaymentMenuButtonSortByAmount() {
        return clientPaymentMenuButtonSortByAmount;
    }

    public String getClientPaymentMenuButtonSortByStatus() {
        return clientPaymentMenuButtonSortByStatus;
    }

    public String getClientPaymentMenuButtonRepeat() {
        return clientPaymentMenuButtonRepeat;
    }

    public String getClientPaymentMenuButtonConfirm() {
        return clientPaymentMenuButtonConfirm;
    }

    public String getUnlockRequestsButtonLogOut() {
        return unlockRequestsButtonLogOut;
    }

    public String getUnlockRequestsButtonAllUsers() {
        return unlockRequestsButtonAllUsers;
    }

    public String getUnlockRequestsButtonUnlockRequests() {
        return unlockRequestsButtonUnlockRequests;
    }

    public String getUnlockRequestsTableNumber() {
        return unlockRequestsTableNumber;
    }

    public String getUnlockRequestsTableBalance() {
        return unlockRequestsTableBalance;
    }

    public String getUnlockRequestsTableCurrency() {
        return unlockRequestsTableCurrency;
    }

    public String getUnlockRequestsTableName() {
        return unlockRequestsTableName;
    }

    public String getUnlockRequestsButtonUnlock() {
        return unlockRequestsButtonUnlock;
    }

    public String getUnlockRequestsButtonSortByName() {
        return unlockRequestsButtonSortByName;
    }

    public String getUnlockRequestsButtonSortByNumber() {
        return unlockRequestsButtonSortByNumber;
    }

    public String getAllUsersButtonLogOut() {
        return allUsersButtonLogOut;
    }

    public String getAllUsersButtonAllUsers() {
        return allUsersButtonAllUsers;
    }

    public String getAllUsersButtonUnlockRequests() {
        return allUsersButtonUnlockRequests;
    }

    public String getAllUsersTableName() {
        return allUsersTableName;
    }

    public String getAllUsersTableCountOfAccount() {
        return allUsersTableCountOfAccount;
    }

    public String getAllUsersTableCountOfCard() {
        return allUsersTableCountOfCard;
    }

    public String getAllUsersButtonShowAllCard() {
        return allUsersButtonShowAllCard;
    }

    public String getAllUsersButtonShowAllAccount() {
        return allUsersButtonShowAllAccount;
    }

    public String getAllUsersButtonUnblock() {
        return allUsersButtonUnblock;
    }

    public String getAllUsersButtonBlock() {
        return allUsersButtonBlock;
    }

    public String getAllUsersButtonSortByName() {
        return allUsersButtonSortByName;
    }

    public String getAllUsersButtonSortByStatus() {
        return allUsersButtonSortByStatus;
    }
}
