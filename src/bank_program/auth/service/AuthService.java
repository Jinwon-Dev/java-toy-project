package bank_program.auth.service;

import bank_program.auth.repository.UserEntity;
import bank_program.auth.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static bank_program.auth.controller.AuthController.startMenu;
import static bank_program.auth.repository.UserRepository.userDB;
import static bank_program.account.presentation.controller.AccountController.userMenu;

public class AuthService {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static boolean isTerminate;

    public static boolean loginState;

    private static UserRepository userRepository = new UserRepository();

    /**
     * 계좌 생성
     */
    public static void createAccount() throws IOException {
        System.out.println("**계좌 생성**\n");

        System.out.print("아이디: ");
        String id = br.readLine();

        System.out.print("비밀번호: ");
        String password = br.readLine();
        System.out.println();

        if (!hasSameIdCheck(id)) {
            Random random = new Random();

            int accountNo = random.nextInt(99999999);

            String number = Integer.toString(accountNo);

            System.out.println("**계좌가 생성되었습니다**");
            System.out.println("**계좌 번호는 " + number + "입니다**\n");

            System.out.print("입금액 : ");
            Long balance = Long.parseLong(br.readLine());

            UserEntity newUser = new UserEntity(id, password, number, balance);

            userRepository.add(newUser);
            startMenu();
        } else {
            System.out.println("**이미 존재하는 ID가 있습니다**");
            startMenu();
        }
    }

    /**
     * 로그인
     */
    public static void logIn() throws IOException {
        System.out.println("**로그인**");
        System.out.print("아이디: ");
        String id = br.readLine();

        System.out.print("비밀번호: ");
        String password = br.readLine();

        if (isMatch(id, password)) {
            System.out.println("**로그인에 성공하였습니다**\n");
            loginState = true;
            userMenu();
        } else {
            System.out.println("**로그인에 실패하였습니다**\n");
            startMenu();
        }
    }

    /**
     * 아이디 및 비밀번호 유효성 검사
     */
    private static boolean isMatch(String id, String password) {

        Iterator<UserEntity> iterator = userDB.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();

            if (id.equals(userEntity.getId()) && password.equals(userEntity.getPassword())) {
                System.out.println("**계좌 번호는 : " + userEntity.getNumber() + "입니다**");
                return true;
            }
        }

        return false;
    }

    /**
     * 아이디 중복 검사
     */
    private static boolean hasSameIdCheck(String id) {
        Iterator<UserEntity> iterator = userDB.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();

            if (id.equals(userEntity.getId())) return true;
        }
        return false;
    }

    /**
     * 로그아웃
     */
    public static void logOut() throws IOException {
        if (loginState) {
            System.out.println("**로그아웃**");
            System.out.println("로그아웃 하시겠습니까?");
            System.out.println("1. 예 | 2. 아니오");

            switch (br.readLine()) {
                case "1" -> {
                    loginState = false;
                    System.out.println("**로그아웃 되었습니다**\n");
                }
                case "2" -> userMenu();
            }
        } else if (!loginState) {
            System.out.println("**로그인을 먼저 해주세요**\n");
            startMenu();
        }
    }

    /**
     * 사용자 탈퇴
     */
    public static void withDrawal() throws IOException {
        System.out.println("**사용자 탈퇴 및 계좌 해지**");

        if (!loginState) {
            System.out.println("**로그인을 먼저 해주세요**\n");
            return;
        }

        System.out.print("탈퇴할 아이디: ");
        String id = br.readLine();

        System.out.print("비밀번호: ");
        String password = br.readLine();

        for (int i = 0; i < userDB.size(); i++) {
            if (isMatch(id, password)) {
                userRepository.remove(i);
                System.out.println("**탈퇴 및 계좌 해지 되었습니다**\n");
                loginState = false;
                startMenu();
                return;
            }
        }

        System.out.println("**아이디나 비밀번호가 일치하지 않습니다**");
    }
}
