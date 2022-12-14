package bank_program.auth.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static bank_program.auth.service.AuthService.*;

public class AuthController {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void startMenu() throws IOException {
        while (!isTerminate) {
            System.out.println("**안녕하세요 진원은행 입니다**");
            System.out.println("1. 계좌 생성 | 2. 사용자 로그인 | 3. 사용자 탈퇴 및 계좌 해지 | 4. 로그아웃 | 5. 종료");
            System.out.print("선택> ");

            int selectNo = Integer.parseInt(br.readLine());

            switch (selectNo) {
                case 1 -> createAccount();
                case 2 -> logIn();
                case 3 -> withDraw();
                case 4 -> logOut();
                case 5 -> {
                    isTerminate = true;
                    System.out.println("**종료되었습니다.**");
                }
            }
        }
    }
}