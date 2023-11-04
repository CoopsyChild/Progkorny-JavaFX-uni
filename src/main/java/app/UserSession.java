package app;

public final class UserSession {
    private static UserSession instance;
    private String username;
    private String lastName;
    private String registrationDate;

    public UserSession(String username, String lastName, String registrationDate) {
        this.username = username;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
    }
    public static UserSession getInstance(String username, String lastName, String registrationDate) {
        if(instance == null) {
            instance = new UserSession(username, lastName, registrationDate);
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void cleanUserSession() {
        username = null;
        lastName = null;
        registrationDate = null;
        instance=null;
    }
}
