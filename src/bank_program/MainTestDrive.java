package bank_program;

import bank_program.auth.controller.AuthController;

import java.io.IOException;

public class MainTestDrive {

    private static AuthController authController = new AuthController();

    public static void main(String[] args) throws IOException {
        authController.startMenu();
    }
}
