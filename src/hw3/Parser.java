/*
 *  Copyright Angelo Bradley 2018
 */

package hw3;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens = new ArrayList<Token>();
    private int nextToken = 0; 
    private final String errorDetected = "THE SAMPLE PROGRAM IS INCORRECT";
    private final String noErrorsDetected = "THE SAMPLE PROGRAM IS CORRECT";
    
    public Parser(ArrayList<Token> externalTokenSet){
        tokens = externalTokenSet;
    }
    
    private boolean tokenSetValidation(){
        int saveStart = nextToken;
        
        if(!tokens.get(nextToken++).value.equals("float"))
           return errorDetected(saveStart);
        
        if(tokens.get(nextToken++).type != Type.FUNCNAME)
            return errorDetected(saveStart);
        
        if(!tokens.get(nextToken++).value.equals("(") && !tokens.get(nextToken++).value.equals(")"))
            return errorDetected(saveStart);
        
        if(!tokens.get(nextToken++).value.equals("{"))
            return errorDetected(saveStart);
        
        if(!tokens.get(nextToken++).value.equals("float"))
            return errorDetected(saveStart);
        
        if(tokens.get(nextToken++).type != Type.IDENT)
            return errorDetected(saveStart);
        
        if(!tokens.get(nextToken++).value.equals(";")){
            if(!tokens.get(nextToken++).value.equals(","))
                return errorDetected(saveStart);
            else {
                while(true){
                    if(tokens.get(nextToken++).type != Type.IDENT)
                        return errorDetected(saveStart);
                    if(tokens.get(nextToken++).value.equals(","))
                        continue;
                    if(tokens.get(nextToken++).value.equals(";"))
                        break;
                    else
                        return errorDetected(saveStart);
                }
            }
        }
        
        if(tokens.get(nextToken++).type != Type.IDENT)
            return errorDetected(saveStart);
        
        if(!tokens.get(nextToken++).value.equals("="))
            return errorDetected(saveStart);
        
        
            
        
        
        
        return true;
    }
    
    public void launchValidationProcess(){
        tokenSetValidation();
    }
    
    public boolean errorDetected(int saveStart){
        System.out.println(errorDetected);
           nextToken = saveStart;
           return false;
    }
}
