package bank_program.auth.repository;

public class UserEntity {
    private String id;
    private String password;

    private String number;

    public UserEntity(String id, String password, String number) {
        this.id = id;
        this.password = password;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}