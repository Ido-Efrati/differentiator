package differentiator;

import differentiator.Token.Type;

/**
 * The parser gets a bunch of tokens from the lexer and determines what
 * expression was written by the user.
 */
public class Parser {
    /**
     * Creates a new parser over the lexer. This parser will use the passed
     * lexer to get tokens--which it will then parse.
     * 
     * @param lexer
     *            The lexer.
     */
    /*
     * grammar part C: 
     * Expression ::= (SUM | MUL| VARIABLES |NUMBERS) 
     * NUMBERS::= ([0-9]*\\.?[0-9]+) // both floats and ints 
     * VARIABLES::= [a-zA-Z]+
     * SUM::= (OPEN_PAREN Expression+Expression CLOSE_PAREN) 
     * MUL ::=(OPEN_PAREN Expression*Expression CLOSE_PAREN) 
     * PARENTHESES::=(OPEN_PAREN|CLOSE_PAREN)
     */
    //lexer is both private and final so it is immuatable 
    // nextToken is private so it can't be changed outside of the class
    private final Lexer lex;
    private Token nextToken ;
    /**
     * Lexer constructor
     * @param lexer take a lexer that will feed tokens
     * @return a tree to feed to the differentiator
     */
    public Parser(Lexer lexer) {
        lex = lexer;
        nextToken= lex.next();
        if (!nextToken.getType().equals(Token.Type.OPEN_PAREN) ){
            throw new RuntimeException("did not start with an open paren");
        }

    }

    /**
     * parse will only work with a valid which means that there are matching
     * parens and that an expression is always between a matching parens (). if
     * the number of parens will not match parse will throw an exception parse
     * will assume correct order for example no + 4 3, so we know that the
     * parens will match
     */
    
    /**
     * a function that will allow differentiator to call Paurse(). This function is going to call ParserExspression(). 
     * ParserExspression is doing the actual parsing process 
     * @return Expression which is my interface to include the binary tree of numbers, variables , sum and multiply
     */
    
    // diff can actually call parserExpression directly. I just wanted to create a wraper function.
    // I don't think it is harmful or damaging the spec. So I am not going to change it based on this comment
   public Expression parse() {
       return this.ParserExspression();
   }

   
   /**
    * this function create the operations that we will store in our tree. the function calls the parseBaseCase to check for the core values
    * Values such as numbers and variables
    * @return Expression which is the multiplication or the sum of numbers or variables or both
    */
   public Expression ParserExspression() {
       Expression left = parseBaseCase();
       if (nextToken.getType().equals(Token.Type.MUL)) {
           nextToken= lex.next();
           Expression right = parseBaseCase();
           Multiply mul= new Multiply(left, right,Type.MUL);
           return mul;
           
       }

       if (nextToken.getType().equals(Token.Type.SUM)) {
           nextToken= lex.next();
           Expression right = parseBaseCase();
           Sum sum= new Sum(left, right, Type.SUM);
           return sum;
       }
       return left; 
   }

       public Expression parseBaseCase() {
           
           if (nextToken.getType().equals(Token.Type.NUMS)) {
              Numbers num = new Numbers(nextToken.getValue(), nextToken.getType());
              nextToken= lex.next();
              return num;
           }
           
           if (nextToken.getType().equals(Token.Type.VARS)) {
               VAR varibale =new VAR(nextToken.getValue(), nextToken.getType());
               nextToken= lex.next();
               return varibale;
           } 

           if (nextToken.getType().equals(Token.Type.OPEN_PAREN)) {
               nextToken= lex.next();
               Expression answer = ParserExspression();
               if (nextToken.getType()!=(Token.Type.CLOSE_PAREN)) {
                   throw new RuntimeException("wrong value was fed to the parser");
               }
               nextToken= lex.next();
               return answer;
           }
           throw new RuntimeException("This is an Exception if we did not match any type of tokens");
           }

       }