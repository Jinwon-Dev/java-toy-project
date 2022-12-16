package bank_program.auth.repository;

public class UserEntity {
    private final String id;
    private final String password;

    private final String number;

    private Long balance;

    public UserEntity(String id, String password, String number, Long balance) {
        this.id = id;
        this.password = password;
        this.number = number;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}