package ozamkovyi.db.entity;

/**
 * Root for admin and client entity.
 *
 * @author O.Zamkovyi
 *
 */

public abstract class User extends Entity{
    protected int id;
    protected String login;
    protected String password;


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
}
