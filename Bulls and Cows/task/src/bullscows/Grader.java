package bullscows;

import java.util.Arrays;

public class Grader {
    private char[] secretCode;
    private int bullCount;
    private int cowCount;


    public Grader(String codeLn, String alphabetLn) {
        System.out.println("Okay, let's start a game!");
        int codeLnInt = Integer.parseInt(codeLn);
        int alphabetLnInt = Integer.parseInt(alphabetLn);
        CodeGenerator generator = new CodeGenerator(codeLnInt, alphabetLnInt);
        this.secretCode = generator.getRandomCode();
        this.bullCount = 0;
        this.cowCount = 0;
    }

    public void processInput(String input) {
        char[] charInput = input.toCharArray();
        this.setBullAndCowCount(charInput);
        this.printStatus();

    }

    public boolean isSecretCode(String input) {
        boolean isWin = true;
        char[] charInput = input.toCharArray();
        for (int i = 0; i < this.secretCode.length; i++) {
            if (this.secretCode[i] != charInput[i]) {
                isWin = false;
            }
        }
        return isWin;
    }

    private void setBullAndCowCount(char[] charInput) {
        this.bullCount = 0;
        this.cowCount = 0;
        for (int i = 0; i < this.secretCode.length; i++) {
            if (this.secretCode[i] == charInput[i]) {
                this.bullCount++;
                continue;
            }
            for (int j = 0; j < this.secretCode.length; j++) {
                if (this.secretCode[i] == charInput[j]) {
                    this.cowCount++;
                }
            }
        }
    }

    private void printStatus() {
        if (this.bullCount == 0 && this.cowCount == 0) {
            System.out.println("Grade: None.");
            return;
        }
        if (this.bullCount != 0 && this.cowCount != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n",
                    this.bullCount, this.cowCount);
            return;
        }
        if (this.cowCount != 0) {
            System.out.printf("Grade: %d cow(s).\n", this.cowCount);
        } else {
            System.out.printf("Grade: %d bull(s).\n", this.bullCount);
        }
    }

    private void setSecretCode(String secretCode) {
        this.secretCode = secretCode.toCharArray();
    }

    private String getSecretCode() {
        return Arrays.toString(this.secretCode).replaceAll("\\[|\\]|\\,|\\ ", "");
    }
    public int getSecretCodeLength() {
        return this.secretCode.length;
    }
}
