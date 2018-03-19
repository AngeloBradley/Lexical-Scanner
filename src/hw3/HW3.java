/*
 *  Copyright Angelo Bradley 2018
 */
package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Angelo Bradley
 * @author Alisea Wiggins
 * @author Teon Parker
 */
public class HW3 {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Token> tokenSet = new ArrayList<Token>();

        System.out.println("Program 1 Analysis: ");
        Tokenizer tokenizer1 = new Tokenizer();
        tokenSet = tokenizer1.setTokenList("program1.txt");

        Parser parser1 = new Parser(tokenSet);
        parser1.ProgramValidationProcess();

        System.out.println();

        System.out.println("Program 2 Analysis: ");
        Tokenizer tokenizer2 = new Tokenizer();
        tokenSet = tokenizer2.setTokenList("program2.txt");

        Parser parser2 = new Parser(tokenSet);
        parser2.ProgramValidationProcess();
    }

}
