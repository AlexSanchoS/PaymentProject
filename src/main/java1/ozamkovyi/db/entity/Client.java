package ozamkovyi.db.entity;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.web.CalendarProcessing;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class Client extends User {

    public static final int ADULTS_AGE = 18;

    protected String name;
    protected Calendar date;
    protected String language;
    protected String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    }

    public boolean isUnblock() {
        status = new ClientDao().getClientStatus(this);
        if (status.equals(Fields.CLIENT_STATUS__UNBLOCK)) {
            return true;
        } else {
            return false;
        }
    }

    public void addNewClientToDB() {
        new ClientDao().addNewClientToDB(this);
    }

    public boolean clientIsAdults() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR)-getDate().get(Calendar.YEAR);
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


}
