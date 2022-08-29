package login_program;

public class User {
    private String id; // 필드
    private String password;
    private String nickname;

    public User(String id, String password, String nickname) { // 생성자
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    public String getId() { // getter
        return id;
    }

    public void setId(String id) { // setter
        this.id = id;
    }

    public String getPassword() { // getter
        return password;
    }

    public void setNickname(String nickname) { // setter
        this.nickname = nickname;
    }

    public String getNickname() { // getter
        return nickname;
    }

    public void setPassword(String password) { // setter
        this.password = password;
    }
}
