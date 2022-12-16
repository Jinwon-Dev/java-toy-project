package bank_program.account.business.service;

import bank_program.auth.repository.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static bank_program.auth.repository.UserRepository.userDB;

public class AccountService {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Iterator<UserEntity> iterator = userDB.iterator();

    /**
     * 계좌 입금
     */
    public static void deposit() throws IOException {

        System.out.println("**계좌 입금**");
        System.out.print("입금하실 계좌 번호를 입력해주세요 : ");
        String number = br.readLine();

        if (isAccountMatch(number)) {
            System.out.println("입금하실 금액을 입력해주세요 : ");
            int amount = Integer.parseInt(br.readLine());
        }
    }

    /**
     * 계좌 번호 검사
     */
    public static boolean isAccountMatch(String number) {
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();

            if (number.equals(userEntity.getNumber())) {
                return true;
            } else System.out.println("계좌번호가 일치하지 않습니다.");
        }

        return false;
    }

    /**
     * 잔액 조회
     */
    public static void accountList() throws IOException {

        System.out.println("**잔액 조회**");
        System.out.print("조회 하실 계좌 번호를 입력하세요 : ");
        String number = br.readLine();

        if (isAccountMatch(number)) {
                System.out.println("잔액은 : ");
            }
    }
}
