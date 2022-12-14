package bank_program.auth.service;

import bank_program.auth.repository.UserEntity;
import bank_program.auth.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static bank_program.auth.controller.AuthController.startMenu;
import static bank_program.auth.repository.UserRepository.userDB;

public class AuthService {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static boolean isTerminate;

    private static boolean loginState;

    private static UserRepository userRepository = new UserRepository();

    /**
     * 계좌 생성
     */
    public static void createAccount() throws IOException {
        System.out.println("**계좌 생성**");
        System.out.println();

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
            System.out.println("**계좌 번호는 " + number + "입니다**");
            System.out.println();

            UserEntity newUser = new UserEntity(id, password, number);

            userRepository.add(newUser);
        } else System.out.println("**이미 존재하는 ID가 있습니다**");
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
            System.out.println("**로그인에 성공하였습니다**");
            System.out.println();
            loginState = true;
        } else {
            System.out.println("**로그인에 실패하였습니다**");
            System.out.println();
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

        loginState = false;
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

            int answer = Integer.parseInt(br.readLine());

            switch (answer) {
                case 1 -> {
                    loginState = false;
                    System.out.println("**로그아웃 되었습니다**");
                    System.out.println();
                }
                case 2 -> startMenu();
            }
        } else if (!loginState) {
            System.out.println("**로그인을 먼저 해주세요**");
            System.out.println();
        }
    }

    /**
     * 사용자 탈퇴
     */
    public static void withDraw() throws IOException {
        System.out.println("**사용자 탈퇴 및 계좌 해지**");

        if (loginState) {
            System.out.print("탈퇴할 아이디: ");
            String id = br.readLine();

            System.out.print("비밀번호: ");
            String password = br.readLine();

            for (int i = 0; i < userDB.size(); i++) {
                if (isMatch(id, password)) {
                    userRepository.remove(i);
                    System.out.println("**탈퇴 및 계좌 해지 되었습니다**");
                    System.out.println();
                    loginState = false;
                } else if (!isMatch(id, password)) {
                    System.out.println("**아이디나 비밀번호가 일치하지 않습니다**");
                    System.out.println();
                }
            }
        } else if (!loginState) {
            System.out.println("**로그인을 먼저 해주세요**");
            System.out.println();
        }
    }
}
