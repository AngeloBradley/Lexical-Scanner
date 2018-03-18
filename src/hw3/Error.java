/*
 *  Copyright Angelo Bradley 2018
 */
package hw3;

/**
 *
 * @author abrad
 */
public enum Error {
    MISSING_OR_INVALID_INIT_KEYWORD(1), 
    INVALID_FUNCTION_NAME(2), 
    FUNCTION_ERROR__MISSING_OPENING_OR_CLOSING_PARENTHESES(3),
    FUNCTION_ERROR__BODY_MISSING_OPENING_BRACKET(4), 
    DECLARATION_ERROR__MISSING_OR_INVALID_KEYWORD(5),
    DECLARATION_ERROR__MISSING_LEFTHAND_VARIABLE(6),
    DECLARATION_ERROR__EXPECTED_SEMICOLON_OR_COMMA(7),
    DECLARATION_ERROR__MISSING_IDENT(8),
    DECLARATION_ERROR__CHECK_END_OF_EXPRESSION(9),
    STMTS_ERROR__MISSING_LEFT_HAND_VAR(10),
    STMTS_ERROR__MISSING_OR_INVALID_ASSIGN_SYMBOL(11),
    STMTS_ERROR__MISSING_RIGHT_HAND_VAR(12),
    STMTS_ERROR__MISSING_OR_INVALID_ARITHMETIC_SYMBOL(13),
    STMTS_ERROR__MISSING_OR_INVALID_VAR(14),
    STMTS_ERROR__ILLEGAL_END_EXPRESSION(15),
    FUNCTION_ERROR_MISSING_CLOSING_BRACKET(16);
    ; 
    
    private int errorIndex;
    
    private Error(int errorIndex){this.errorIndex = errorIndex;}
    
    public static Error getError(int errorIndex){
        for(Error err : Error.values()){
            if(err.errorIndex == errorIndex) return err;
        }
        
        throw new IllegalArgumentException("Undefined Error");
    }
}
