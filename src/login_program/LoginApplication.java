package login_program;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginApplication {
    private static User[] userArray = new User[10]; // User 배열 생성
    private static Scanner scanner = new Scanner(System.in);

    private static boolean isTerminate; // 종료 상태

    private static int userCount; // 회원가입한 유저수

    private static boolean loginState; // 로그인 상태

    public static void main(String[] args) {
//        boolean run = true;
        try {
            do {
                System.out.println("------------------------------------------------");
                System.out.println("1.회원가입 | 2.로그인 | 3.사용자조회 | 4.탈퇴 | 5.종료");
                System.out.println("------------------------------------------------");
                System.out.print("선택> ");

                int selectNo = scanner.nextInt();

                if (selectNo == 1) {
                    createAccount();
                } else if (selectNo == 2) {
                    logIn();
                } else if (selectNo == 3) {
                    userLookUp();
                } else if (selectNo == 4) {
                    withDraw();
                } else if (selectNo == 5) {
                    isTerminate = true;
                }
            } while (!isTerminate);
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            System.out.println("프로그램을 다시 실행하세요.");
        }

        System.out.println("프로그램이 종료되었습니다.");
    }

    private static void createAccount() { // 회원가입
        System.out.println("[회원가입]");
        System.out.println();

        System.out.print("아이디: ");
        String id = scanner.next();

        System.out.print("비밀번호: ");
        String password = scanner.next();

        System.out.print("닉네임: ");
        String nickname = scanner.next();

        User newUser = new User(id, password, nickname);

        if (userCount < 10) {
            userArray[userCount++] = new User(id, password, nickname);
            System.out.println("회원가입 되었습니다.");
            return;
        }

        System.out.println("회원가입에 실패하였습니다.");
    }

    private static void logIn() { // 로그인
        System.out.print("아이디: ");
        String id = scanner.next(); // id 받기

        System.out.print("비밀번호: ");
        String password = scanner.next(); // password 받기

        for (int i = 0; i < userArray.length; i++) {
            if ((id.equals(userArray[i].getId())) && (password.equals(userArray[i].getPassword()))) { // 로그인할 아이디와 비밀번호가 일치하면
                System.out.println("로그인에 성공하였습니다.");
                System.out.println("아이디: " + userArray[i].getId() + ", " + "닉네임: " + userArray[i].getNickname()); // 로그인된 아이디와 닉네임 출력
                loginState = true;
                break;
            } else { // 로그인할 아이디와 비밀번호가 일치하지 않으면
                System.out.println("로그인에 실패하였습니다."); // 로그인 실패 메시지
                break;
            }
        }
    }

    private static void userLookUp() { // 사용자 조회
        if (loginState) { // 로그인을 한 상태
            System.out.println("[사용자조회]");
            System.out.println();

            for (User user : userArray) { // 향상된 for 문
                if (user != null) { // null은 출력하지 않음
                    System.out.print("닉네임: " + user.getNickname()); // 회원들의 닉네임 출력
                    System.out.println();
                }
            }
        } else { // 로그인을 안한 상태
            System.out.println("로그인을 먼저 하세요.");
        }
    }

    private static void withDraw() { // 탈퇴
        if (loginState) {
            System.out.print("탈퇴할 아이디: ");
            String id = scanner.next();

            System.out.print("비밀번호: ");
            String password = scanner.next();

            for (int i = 0; i < userArray.length; i++) {
                if ((id.equals(userArray[i].getId())) && (password.equals(userArray[i].getPassword()))) { // 로그인할 아이디와 비밀번호가 일치하면

                    userArray[i] = new User(null, null, null);

                    System.out.println("탈퇴되었습니다."); // 탈퇴 완료 메시지
                    break;
                } else { // 로그인할 아이디와 비밀번호가 일치하지 않으면
                    System.out.println("탈퇴에 실패하였습니다."); //  탈퇴 실패 메시지
                    break;
                }
            }
        } else {
            System.out.println("로그인을 먼저 하세요.");
        }
    }
}
