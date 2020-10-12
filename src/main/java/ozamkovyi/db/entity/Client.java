package ozamkovyi.db.entity;

import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.web.CalendarProcessing;
import ozamkovyi.web.Localization;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class Client extends Entity {

    public static final int ADULTS_AGE = 18;

    private int id;
    private String login;
    private String password;
    private String name;
    private Calendar date;
    private String language;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", language=" + language +
                '}' + id;
    }

    public void createNewClientByRequest(HttpServletRequest request, Localization localization) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        Calendar date1 = CalendarProcessing.string2Date(date);
        Client client = new Client();
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setDate(date1);
        client.setLanguage(localization.getLocal());
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
}
