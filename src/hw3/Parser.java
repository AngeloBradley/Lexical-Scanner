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

    public Parser(ArrayList<Token> externalTokenSet) {
        tokens = externalTokenSet;
    }

    private boolean tokenSetValidation() {
        int saveStart = nextToken;

        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(1);
        }

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
            //System.out.println(tokens.get(nextToken - 1).value);
            return errorDetected(4);
        }

        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(5);
        }

        if (tokens.get(nextToken++).type != Type.IDENT) {
            return errorDetected(6);
        }

        if (!tokens.get(nextToken).value.equals(";")) {
            if (!tokens.get(nextToken++).value.equals(",")) {
                //System.out.println(tokens.get(nextToken - 1).value);
                return errorDetected(7);
            } else {
                while (true) {
                    if (tokens.get(nextToken++).type != Type.IDENT) {
                        return errorDetected(8);
                    }

                    if (tokens.get(nextToken).value.equals(",")) {
                        nextToken++;
                        continue;
                    }
                    if (tokens.get(nextToken++).value.equals(";")) {
                        break;
                    } else {
                        return errorDetected(9);
                    }

                }
            }
        }

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

                if (tokens.get(nextToken++).type != Type.ARITH) {
                    return errorDetected(13);
                } else {
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
                            //System.out.println(tokens.get(nextToken - 1).value);
                            //System.out.println(tokens.get(nextToken).value);
                            //System.out.println(tokens.get(nextToken + 1).value);
                            return errorDetected(15);
                        }
                    }
                }
            }
            if (tokens.get(nextToken).value.equals("}")) {
                break;
            }
        }

        if (!tokens.get(tokens.size()-1).value.equals("}")) {
            return errorDetected(16);
        }

        return true;
    }

    public void ProgramValidationProcess() {
        System.out.println(tokenSetValidation() ? noErrorsDetected : errorDetected);
    }

    private boolean errorDetected(int errorNum) {
        System.out.println("Detected Error: " + Error.getError(errorNum));
        nextToken = 0;
        return false;
    }
}
