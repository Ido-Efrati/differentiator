package differentiator;

/**
 * A token is a lexical item that the parser uses.
 */
public class Token {
    private final Type type;
    private final String value;
    /**
     * All the types of tokens that can be made.
     */
    /*
     * part A:
     * grammar will contain: operations + and * , open and close parentheses,
     * numbers(ints or floats) and variables   
     */
    public static enum Type {   
        SUM, // for + 
        MUL, // for *
        OPEN_PAREN, // for (
        CLOSE_PAREN, // for )
        NUMS, // for integers
        VARS, // for variables like x y ....
        EOF, // end of file 
            }
    // generate Token function , will be used in Lexer to generate tokens
    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }
    
    //Two getters functions to return the type or the value 
    public Type getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }


    }