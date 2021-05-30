package bullscows;

public class Validator extends SecretCodeBase{
    public Validator() {

    }
    public Validator(int codeLn, int alphabetLn) {
        this.codeLn = codeLn;
        this.alphabetLn = alphabetLn;
    }

    public boolean isValidLns(String codeLn, String alphabetLn) {
        boolean isValid = true;
        int codeLnInt;
        int alphabetLnInt;

        if (isValidNumber(codeLn)) {
            codeLnInt = Integer.parseInt(codeLn);
        } else {
            return false;
        }

        if (isValidNumber(alphabetLn)) {
            alphabetLnInt = Integer.parseInt(alphabetLn);
        } else {
            return false;
        }

        if (codeLnInt > alphabetLnInt) {
            System.out.printf(
                    "Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n",
                    codeLnInt, alphabetLnInt);
            isValid = false;
        }

        if (codeLnInt >= 36) {
            System.out.printf(
                    "Error: can't generate a secret number with a length of %d because there aren't enough unique digits.",
                    codeLnInt);
            return false;
        }

        return isValid;
    }

    public boolean isValidNumber(String num) {
        boolean isNumber = true;
        char[] numCharArr = num.toCharArray();
        for (char c : numCharArr) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                System.out.printf("Error: \"%s\" isn't a valid number.", num);
                break;
            }
        }
        if (isNumber) {
            if (Integer.parseInt(num) > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return false;
            }
        }
        if (isNumber) {
            if (Integer.parseInt(num) == 0) {
                System.out.println("Error: min number is 1");
                return false;
            }
        }
        return isNumber;
    }
    public boolean isValidRq (Grader grader, String strRq) {
        boolean isValid = true;
        if (grader.getSecretCodeLength() != strRq.length()) {
            isValid = false;
            System.out.printf("Error: %s is not valid len secret code != len rq \n", strRq);
        }

        return isValid;
    }
}
