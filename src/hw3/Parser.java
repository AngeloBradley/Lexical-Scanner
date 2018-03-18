/*
 *  Copyright Angelo Bradley 2018
 */
package hw3;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens = new ArrayList<Token>();
    private ArrayList<Integer> steps = new ArrayList<Integer>();
    private int nextToken = 0;
    private final String errorDetected = "THE SAMPLE PROGRAM IS INCORRECT";
    private final String noErrorsDetected = "THE SAMPLE PROGRAM IS CORRECT";

    public Parser(ArrayList<Token> externalTokenSet) {
        tokens = externalTokenSet;
    }

    private boolean tokenSetValidation() {
        int saveStart = nextToken;
        steps.add(1);
        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(saveStart);
        }
        steps.add(2);
        if (tokens.get(nextToken++).type != Type.FUNCNAME) {
            return errorDetected(saveStart);
        }
        steps.add(3);
        if (!tokens.get(nextToken++).value.equals("(")) {
            return errorDetected(saveStart);
        }
        if (!tokens.get(nextToken++).value.equals(")")) {
            return errorDetected(saveStart);
        }
        steps.add(4);
        if (!tokens.get(nextToken++).value.equals("{")) {
            //System.out.println(tokens.get(nextToken - 1).value);
            return errorDetected(saveStart);
        }
        steps.add(5);
        if (!tokens.get(nextToken++).value.equals("float")) {
            return errorDetected(saveStart);
        }
        steps.add(6);
        if (tokens.get(nextToken++).type != Type.IDENT) {
            return errorDetected(saveStart);
        }

        if (!tokens.get(nextToken).value.equals(";")) {
            steps.add(7);
            if (!tokens.get(nextToken++).value.equals(",")) {
                //System.out.println(tokens.get(nextToken - 1).value);
                return errorDetected(saveStart);
            } else {
                while (true) {
                    steps.add(8);
                    if (tokens.get(nextToken++).type != Type.IDENT) {
                        //System.out.println(tokens.get(nextToken - 1).value);
                        //System.out.println(tokens.get(nextToken).value);
                        //System.out.println(tokens.get(nextToken + 1).value);
                        return errorDetected(saveStart);
                    }
                    steps.add(9);
                    if (tokens.get(nextToken).value.equals(",")) {
                        nextToken++;
                        continue;
                    }
                    steps.add(10);
                    if (tokens.get(nextToken++).value.equals(";")) {
                        break;
                    } else {
                        //System.out.println(tokens.get(nextToken - 1).value);
                        //System.out.println(tokens.get(nextToken).value);
                        //System.out.println(tokens.get(nextToken + 1).value);
                        return errorDetected(saveStart);
                    }
                }
            }
        }

        while (true) {
            //if next token is not variable ->error
            steps.add(11);
            if (tokens.get(nextToken++).type != Type.IDENT) {
                return errorDetected(saveStart);
            }
            //if the variable is not followed by an = sign -> error
            steps.add(12);
            if (!tokens.get(nextToken++).value.equals("=")) {
                return errorDetected(saveStart);
            }

            //if next token is not variable ->error (looking at something at a minimum
            //that looks like m = n
            steps.add(13);
            if (tokens.get(nextToken++).type != Type.IDENT) {
                return errorDetected(saveStart);
            }

            //if you do not something like m = n; then what follows
            //must be an expression
            if (!tokens.get(nextToken).value.equals(";")) {
                //the following token must be a arithemtic symbol (+ or -)
                //if not -> error
                steps.add(14);
                if (tokens.get(nextToken++).type != Type.ARITH) {
                    return errorDetected(saveStart);
                } else {
                    while (true) {
                        steps.add(15);
                        if (tokens.get(nextToken++).type != Type.IDENT) {
                            return errorDetected(saveStart);
                        }
                        if (tokens.get(nextToken).type == Type.ARITH) {
                            nextToken++;
                            continue;
                        }
                        steps.add(16);
                        if (tokens.get(nextToken++).value.equals(";")) {
                            break;
                        } else {
                            //System.out.println(tokens.get(nextToken - 1).value);
                            //System.out.println(tokens.get(nextToken).value);
                            //System.out.println(tokens.get(nextToken + 1).value);
                            return errorDetected(saveStart);
                        }
                    }
                }
            }
            if (tokens.get(nextToken).value.equals("}")) {
                break;
            }
        }
        return true;
    }

    public void launchValidationProcess() {
        tokenSetValidation();
    }

    public boolean errorDetected(int saveStart) {
        System.out.println(errorDetected);
        System.out.println(steps.get(steps.size() - 1));
        nextToken = saveStart;
        return false;
    }
}
