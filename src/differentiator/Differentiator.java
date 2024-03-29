package differentiator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import differentiator.Token.Type;


/** Symbolic differentiator */
public class Differentiator {
    /**
     * Differentiates the passed expression string with respect to variable
     * and returns its derivative as a string.  If the expression or variable
     * is null, behavior is undefined.  If the expression is not parsable,
     * throws an exception.
     * @param expression The expression.
     * @param variable The variable to differentiate by.
     * @return The expression's derivative.
     */
    public String evaluate(String expression, String variable) {
        // NOTE: Remember to implement differentiation with a visitor pattern.
        // NOTE: Remember to implement toString() with the interpreter pattern.
        //create the tree to evaluate
        Expression tree=treeToEval(expression);
        
        //creating a visitor
        Differentiator_Visitor v = new Differentiator_Visitor();
        //the tree will accept the visitor and return the evaluated information
        Expression eval = (Expression) tree.accept(v,variable);
        
        return eval.toString();
    }
    
    //just a way to get the tree - added a specs to this helper function base on a code review comment
    /**
     * 
     * @param take a string expression- to generate a binary tree form it
     * @return a binary tree to perform differentiation on
     */
    public Expression treeToEval(String expression){
        Lexer lex = new Lexer(expression);
        Parser p = new Parser(lex);
        Expression Tree= p.parse();
        return Tree;
    }

    /**
     * Repeatedly reads expressions from the console, and outputs the results of
     * evaluating them. Inputting an empty line will terminate the program.
     * @param args unused
     */
    
    public static void main(String[] args) throws IOException {
        String result;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String expression;
        do {
            // display prompt
            System.out.print("> ");
            // read input
            expression = in.readLine();
            // terminate if input empty
            if (!expression.equals("")) {
                try {
                    Differentiator diff = new Differentiator();
                    result = diff.evaluate(expression, "x");
                    System.out.println(result);
                } catch (RuntimeException re) {
                    System.err.println("Error: " + re.getMessage());
                }
            }
        } while (!expression.equals(""));
    }
}
