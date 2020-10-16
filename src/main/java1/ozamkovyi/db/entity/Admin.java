package java1.ozamkovyi.db.entity;

public class Admin extends User {
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + super.getLogin() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
