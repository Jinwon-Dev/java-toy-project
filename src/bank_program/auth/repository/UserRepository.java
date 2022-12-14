package bank_program.auth.repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<UserEntity> userDB = new ArrayList<>();

    public void add(UserEntity userEntity) {
        userDB.add(userEntity);
    }

    public void remove(int i) {
        userDB.remove(i);
    }
}
