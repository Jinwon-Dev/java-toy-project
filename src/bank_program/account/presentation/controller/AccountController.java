package bank_program.account.presentation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static bank_program.account.business.service.AccountService.*;
import static bank_program.auth.service.AuthService.*;
import static bank_program.auth.service.AuthService.isTerminate;

public class AccountController {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void userMenu() throws IOException {
        while (!isTerminate & loginState) {
            System.out.println("**원하는 메뉴를 선택하세요**");
            System.out.println("1. 계좌 입금 | 2. 예금 출금 | 3. 잔액 조회 | 4. 거래 내역 출력 | 5. 사용자 탈퇴 및 계좌 해지 | 6. 로그아웃 | 7. 종료");
            System.out.print("선택> ");

            switch (br.readLine()) {
                case "1" -> deposit();
                case "2" -> withDraw();
                case "3" -> checkBalance();
                case "5" -> withDrawal();
                case "6" -> logOut();
                case "7" -> {
                    isTerminate = true;
                    System.out.println("**종료되었습니다**");
                }
            }
        }
    }
}