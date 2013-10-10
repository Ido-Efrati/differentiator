package differentiator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import differentiator.Token.Type;

/**
 * A lexer takes a string and splits it into tokens that are meaningful to a
 * parser.
 */
public class Lexer {
    /**
     * Creates the lexer over the passed string.
     * 
     * @param string
     *            The string to tokenize.
     */
    private String myString;
    private String MyStringWithspaces;
    private int i = 0;
    private final Matcher matcher;
    
    // Regex matching the next token for all of the valid strings
    private static final Pattern TOKEN_REGEX = Pattern.compile(
            "([a-zA-Z]+)" // VARS
            + "|" + "([0-9]*\\.?[0-9]+)" // NUMS both ints and floats
            + "|" + "(\\+)" // SUM
            + "|" + "(\\*)" // MUL
            + "|" + "(\\()" // OPEN_PAREN
            + "|" + "(\\))" // CLOSE_PAREN*/

    );

    // The token types for each of the parenthesized patterns in TOKEN_REGEX.
    private static final Type[] TOKEN_TYPES = { Type.VARS,// for variables like
                                                          // x y
            Type.NUMS, // for integers
            Type.SUM,//
            Type.MUL,//
            Type.OPEN_PAREN, // for ( //
            Type.CLOSE_PAREN // for )//
    };

    /*
     * The lexer will receive a string, if there are spaces in the string it
     * will remove them. the string can only contain A-Z, a-z , + , * , ( ,) and
     * 0-9 anything else will trigger a runtime exception Required helper
     * functions: TOKEN_REGEX- that will recognize patterns in the string for
     * the token TOKEN_TYPES- will map the patterns to tokens Token- generate
     * the tokens for the parser
     */
    
    /**
     *  lexer will take a string and return a token.
     * @param string The string to create tokens for.
     * @return token to give to Parser
     */
    //Check to verify right amount of paren to fix the testExtraRParen_dx (x)) bug
    public Lexer(String string) {
        int parenCounter = 0;
        for(int location=0; location < string.length();location++){
            if (string.charAt(location)==('(')){
                parenCounter++;
            }
            if(string.charAt(location)==(')')){
                parenCounter--;
            }
        }
        if(parenCounter!=0){
            throw new RuntimeException("number of paren is not right");
        }

        
        
        // myString is the string that we're parsing,

        // Testing the invalid order of variables in the string
        // I won't allow x3 or 3x , if they are missing an operation between
        // them
       for (int i = 1; i < string.length() - 1; i++) {
            if ((Character.isDigit(string.charAt(i)) && Character
                    .isLetter(string.charAt(i + 1)))
                    || ((Character.isLetter(string.charAt(i)) && Character
                            .isDigit(string.charAt(i + 1))))) {
                throw new RuntimeException(
                        "illegal string missing an operation between vars");
            }
        }
       //fixing the trailing variable bug for both dx and dy
       if (string.charAt(string.length()-1)!=(')')){
           throw new RuntimeException(
                   "we have a trailing variable");
       }
        myString = string.replaceAll(" ", ""); // a string without whitespace
        MyStringWithspaces=string;
        matcher = TOKEN_REGEX.matcher(myString);
    }



    // we need to tokenize all of the string, or return a runtime exception if
    // this is an invalid token
    public Token next() throws RuntimeException {
        /*
         * when we get to the end of the file, just return nothing
         */
        if (i >= myString.length()) {
            return new Token(Type.EOF, "");
        }

        // we will start look for the next token
        // Case 1: no token was found
        if (!matcher.find(i)) {
            throw new RuntimeException("syntax error at "
                    + myString.substring(i) + " is not a valid token");
        }
        // Case2: token was found
        // Get the part of the string that the regex matched,
        // and advance our state
        String value = matcher.group(0);

        i = matcher.end();


        // Each set of parentheses in TOKEN_REGEX is a "capturing group", which
        // means that the regex matcher remembers where it matched and returns
        // it
        for (int i = 1; i <= matcher.groupCount(); ++i) {
            if (matcher.group(i) != null) {
                // add a case to check for f o o due to the bug MissingOperandVars
                // this also adress a comment in my code review
                if(MyStringWithspaces.contains(value)){
                return new Token(TOKEN_TYPES[i - 1], value);}
                else{ 
                    throw new RuntimeException("var is missing an operation");
                }
            }
        }

        // if we got here, there's a bug in the regex -- Matcher said we matched
        // the expression, but it didn't match any of the parens. We need to
        // throw an error
        throw new AssertionError("shouldn't get here");

    }
}
