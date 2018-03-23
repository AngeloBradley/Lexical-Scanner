/*
 *  Copyright Angelo Bradley 2018
 */
package hw3;

import java.util.ArrayList;

/**
 * @author Angelo Bradley
 * @author Alisea Wiggins
 * @author Teon Parker
 */
public class Parser {

    private ArrayList<Token> tokens = new ArrayList<Token>();
    private int nextToken = 0;
    private final String errorDetected = "THE SAMPLE PROGRAM IS INCORRECT";
    private final String noErrorsDetected = "THE SAMPLE PROGRAM IS CORRECT";

    //Parser constructor that accepts a list of tokens
    public Parser(ArrayList<Token> externalTokenSet) {
        tokens = externalTokenSet;
    }

    private boolean tokenSetValidation() {
        //////////////////////////////////////////////////////////////
        //This section checks the program header
        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(1);
        }
        //The check here is performed on the basis of type instead of value 
        //because the program name can vary from program to program
        if (tokens.get(nextToken++).type != Type.FUNCNAME) {
            return errorDetected(2);
        }

        if (!tokens.get(nextToken++).value.equals("(")) {
            return errorDetected(3);
        }
        if (!tokens.get(nextToken++).value.equals(")")) {
            return errorDetected(3);
        }

        if (!tokens.get(nextToken++).value.equals("{")) {
            return errorDetected(4);
        }
        ///////////////////////////////////////////////////////////////
        //This section checks the body of the program 
        //
        //The first token should be the keyword float following by 1 or more variables separated by commas
        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(5);
        }

        if (tokens.get(nextToken++).type != Type.IDENT) {
            return errorDetected(6);
        }
        //If the next token, after the first variable, is not a semicolon then it MUST be a comma
        if (!tokens.get(nextToken).value.equals(";")) {
            //If you found neither a semicolon nor a comma -> ERROR
            if (!tokens.get(nextToken++).value.equals(",")) {
                return errorDetected(7);
            } else {
                //This while loop iterates through the list of variables (and commas) that make up
                //the rest of the declaration (<declare>) statement
                while (true) {
                    if (tokens.get(nextToken++).type != Type.IDENT) {
                        return errorDetected(8);
                    }

                    if (tokens.get(nextToken).value.equals(",")) {
                        nextToken++;
                        continue;
                    }
                    //The loop breaks when a semicolon is reached
                    //If the neither a comma nor a semicolon is found following a variable (<ident>) -> ERROR
                    if (tokens.get(nextToken++).value.equals(";")) {
                        break;
                    } else {
                        return errorDetected(9);
                    }

                }
            }
        }
        
        //This section checks the <stmts> portion of the body where variables receive values
        //either by direct copying or as the result of some arithemtic operation
        while (true) {

            if (tokens.get(nextToken++).type != Type.IDENT) {
                return errorDetected(10);
            }

            if (!tokens.get(nextToken++).value.equals("=")) {
                return errorDetected(11);
            }

            if (tokens.get(nextToken++).type != Type.IDENT) {
                return errorDetected(12);
            }

            if (!tokens.get(nextToken).value.equals(";")) {
                
                //if a semicolon is not found after the second variable (<ident>)
                //then what follows MUST be token of type ARITH ({+|-})
                //if not -> ERROR
                if (tokens.get(nextToken++).type != Type.ARITH) {
                    return errorDetected(13);
                } else {
                    //This while loop checks all of the remaining tokens in the expression
                    //breaking if the end of the expression is reached
                    //throwing an error in a valid end of expression is not reached
                    while (true) {
                     
                        if (tokens.get(nextToken++).type != Type.IDENT) {
                            return errorDetected(14);
                        }
                        if (tokens.get(nextToken).type == Type.ARITH) {
                            nextToken++;
                            continue;
                        }
                        if (tokens.get(nextToken++).value.equals(";")) {
                            break;
                        } else {
                            
                            return errorDetected(15);
                        }
                    }
                }
            }
            if (tokens.get(nextToken).value.equals("}")) {
                break;
            }
        }
        //this reinforces that the program should end with a closing curly brace
        if (!tokens.get(tokens.size()-1).value.equals("}")) {
            return errorDetected(16);
        }

        return true;
    }

    //This is the public method for initializing the token validation
    //It also prints to a console the result of the validation process
    public void ProgramValidationProcess() {
        System.out.println(tokenSetValidation() ? noErrorsDetected : errorDetected);
    }

    //This method is called if an error is detected
    //An error number is passed to it which it uses to retrieve and print the specific error
    private boolean errorDetected(int errorNum) {
        System.out.println("Detected Error: " + Error.getError(errorNum));
        nextToken = 0;
        return false;
    }
}
