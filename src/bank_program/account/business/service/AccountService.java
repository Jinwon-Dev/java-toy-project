package bank_program.account.business.service;

import bank_program.auth.repository.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static bank_program.auth.repository.UserRepository.userDB;

public class AccountService {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * 계좌 입금
     */
    public static void deposit() throws IOException {

        System.out.println("**계좌 입금**");
        System.out.print("입금하실 계좌 번호를 입력해주세요 : ");
        String number = br.readLine();

        System.out.print("입금하실 금액을 입력해주세요 : ");
        Long amount = Long.parseLong(br.readLine());

        UserEntity user = findUser(number);

        try {
            user.setBalance(user.getBalance() + amount);
            System.out.println("입금이 완료되었습니다.");

            if (isAccountMatch(number)) { // 계좌 번호가 일치하면
                System.out.println("잔액은 : " + user.getBalance() + "\n");
            }
        } catch (NullPointerException e) {
            System.out.println("잘못된 계좌 번호 입니다.");
        }
    }

    /**
     * 예금 출금
     */
    public static void withDraw() throws IOException {
        System.out.println("**예금 출금**");
        System.out.print("출금하실 계좌 번호를 입력해주세요 : ");
        String number = br.readLine();

        System.out.print("출금하실 금액을 입력해주세요 : ");
        Long amount = Long.parseLong(br.readLine());

        UserEntity user = findUser(number);

        if (isAccountMatch(number)) { // 계좌 번호가 일치하면
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                System.out.println("출금이 완료되었습니다.");
                System.out.println("잔액은 : " + user.getBalance() + "원 입니다.\n");

            } else {
                System.out.println("출금하려는 금액이 너무 많습니다.");
            }
        }
    }

    /**
     * 계좌 번호가 일치하는지 검사
     */
    public static boolean isAccountMatch(String number) {
        Iterator<UserEntity> iterator = userDB.iterator();

        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();

            if (number.equals(userEntity.getNumber())) {
                return true;
            } else {
                System.out.println("고객님의 계좌 번호가 아닙니다.\n");
                return false;
            }
        }

        return false;
    }

    /**
     * 계좌 번호로 특정한 사용자를 반환
     */
    public static UserEntity findUser(String number) {
        Iterator<UserEntity> iterator = userDB.iterator();

        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();

            if (number.equals(userEntity.getNumber())) {
                return userEntity;
            }
        }

        return null;
    }

    /**
     * 잔액 조회
     */
    public static void checkBalance() throws IOException {

        System.out.println("**잔액 조회**");
        System.out.print("조회 하실 계좌 번호를 입력하세요 : ");
        String number = br.readLine();

        if (isAccountMatch(number)) {
            UserEntity user = findUser(number);
            System.out.println("잔액은 : " + user.getBalance() + "원 입니다.");
        }
    }

    /**
     * 거래 내역 출력
     */
    public static void findHistory() {

    }
}
