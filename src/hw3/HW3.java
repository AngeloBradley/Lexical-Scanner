/*
 *  Copyright Angelo Bradley 2018
 */
package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Angelo Bradley
 */
public class HW3 {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Token> tokenSet = new ArrayList<Token>();

        Tokenizer tokenizer = new Tokenizer();

        tokenSet = tokenizer.setTokenList("program1.txt");

        for (Token each : tokenSet) {
            System.out.println(each.value + " " + each.type);
        }

        Parser parser = new Parser(tokenSet);
        parser.launchValidationProcess();
    }

}
