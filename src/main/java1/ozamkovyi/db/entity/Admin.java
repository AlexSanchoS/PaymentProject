package ozamkovyi.db.entity;

public class Admin extends User {
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
