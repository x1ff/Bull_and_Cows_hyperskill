package bullscows;

import java.util.Random;

public class CodeGenerator extends SecretCodeBase {

    private char[] unusedSymbolForCode;

    public CodeGenerator(int codeLn, int alphabetLn) {
        this.codeLn = codeLn;
        this.alphabetLn = alphabetLn;
        this.unusedSymbolForCode = new char[alphabetLn];
        for (int i = 0; i < alphabetLn; i++) {
            this.unusedSymbolForCode[i] = this.possibleSymbols[i];
        }
    }

    public char[] getRandomCode() {
        char[] code = new char[this.codeLn];
        for (int i = 0; i < this.codeLn; i++) {
            char pseudoRandomSymbol = 'A';
            while (pseudoRandomSymbol == 'A') {
                Random random = new Random();
                char randomChar = this.unusedSymbolForCode[random.nextInt(this.unusedSymbolForCode.length)];
                // System.out.println("INFO: random char " + randomChar);
                if (isUnusedChar(randomChar)) {
                    pseudoRandomSymbol = randomChar;
                }
            }
            code[i] = pseudoRandomSymbol;
            // System.out.println(this.unusedSymbolForCode);
            updateUnusedSymbol(pseudoRandomSymbol);
            // System.out.println("INFO: secretCode" + Arrays.toString(code));
        }
        String hiddenCode = String.format("%" + codeLn + "s", "*").replace(' ', '*');
        if (this.alphabetLn > 10) {
            System.out.printf("The secret is prepared: %s (0-9, a-%s).", hiddenCode, this.possibleSymbols[this.alphabetLn - 1]);
        } else {
            System.out.printf("The secret is prepared: %s (0-%d).", hiddenCode, this.alphabetLn - 1);
        }
        return code;
    }

    private boolean isUnusedChar (char c) {
        boolean flag = false;
        for (char value : this.unusedSymbolForCode) {
            if (value == c) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void updateUnusedSymbol(char c) {
        for (int i = 0; i < this.unusedSymbolForCode.length; i++) {
            if (this.unusedSymbolForCode[i] == c) {
                char[] copy = new char[unusedSymbolForCode.length - 1];
                System.arraycopy(this.unusedSymbolForCode, 0, copy, 0, i);
                System.arraycopy(this.unusedSymbolForCode, i + 1, copy, i, this.unusedSymbolForCode.length - i - 1);
                this.unusedSymbolForCode = copy;
                break;
            }
        }
    }
}
