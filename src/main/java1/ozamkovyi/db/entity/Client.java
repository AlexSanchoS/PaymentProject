package ozamkovyi.db.entity;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.web.CalendarProcessing;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Client extends User {

    public static final int ADULTS_AGE = 18;

    private String name;
    private Calendar date;
    private String language;
    private String status;
    private int accountCount;
    private int creditCardCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }


    public int getCreditCardCount() {
        return creditCardCount;
    }

    public void setCreditCardCount(int creditCardCount) {
        this.creditCardCount = creditCardCount;
    }

    public String getStatus() {
        return status;
    }

    public String getBlocButton(ResourceBundle bundle) {
        if (status.equals(Fields.CLIENT_STATUS__BLOCK)) {
            return bundle.getString("allUsers_jsp.button.unblock");
        } else {
            return bundle.getString("allUsers_jsp.button.block");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + super.getLogin() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", language=" + language +
                '}';
    }

    public void createNewClientByRequest(HttpServletRequest request, String locale) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        Calendar date1 = CalendarProcessing.string2Date(date);
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setDate(date1);
        this.setStatus(Fields.CLIENT_STATUS__UNBLOCK);
        this.setLanguage(locale);
        System.out.println(this.name);
    }

    public boolean clientIsAdults() {
        Calendar cal = Calendar.getInstance();
        int year = getDate().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (year > Client.ADULTS_AGE) {
            return true;
        } else {
            if (year < Client.ADULTS_AGE) {
                return false;
            }
        }
        int month = this.getDate().get(Calendar.MONTH) - cal.get(Calendar.MONTH);
        if (month > 0) {
            return true;
        } else {
            if (month < 0) {
                return true;
            }
        }
        int day = this.getDate().get(Calendar.DAY_OF_WEEK_IN_MONTH) - cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        if (day < 0) {
            return false;
        }
        return true;
    }

    public void addNewClientToDB() {
        ClientDao clientDao = new ClientDao();
        clientDao.addNewClientToDB(this);
    }

    public boolean isUnblock() {
        status = ClientDao.getClientStatus(this);
        if (status.equals(Fields.CLIENT_STATUS__UNBLOCK)) {
            return true;
        }else{
            return false;
        }
    }
}
