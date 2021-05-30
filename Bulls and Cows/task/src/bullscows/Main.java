package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Validator validator = new Validator();
        System.out.println("Input the length of the secret code:");
        String codeLn = sc.nextLine();
        if (!validator.isValidNumber(codeLn)) {
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        String alphabetLn = sc.nextLine();
        if (!validator.isValidNumber(alphabetLn)) {
            return;
        }
        if (validator.isValidLns(codeLn, alphabetLn)) {
            Grader grader = new Grader(codeLn, alphabetLn);
            boolean isEndGame = false;
            int turnCount = 0;
            validator = new Validator(Integer.parseInt(codeLn), Integer.parseInt(alphabetLn));
            while (!isEndGame) {
                System.out.printf("Turn %d:\n", ++turnCount);
                String inputCode = sc.next();
                if(validator.isValidRq(grader, inputCode)) {
                    grader.processInput(inputCode);
                    isEndGame = grader.isSecretCode(inputCode);
                }
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }
}
