package differentiator;
import static org.junit.Assert.*;

import org.junit.Test;

import differentiator.Token.Type;

public class ParserTest {
  //All of the test have comments to make them easier to understand
  // valid tests - a number, a var, complex expression of both sum, mul , floats ,integers and vars
  // invalid expression that is missing parens, expression with illegal token.
  // more invalid test were added to the differentiatorTest class
    @Test

    public void TestANumber() {
        //parsing a number
        Lexer lex = new Lexer("(5.0)");
        Parser p = new Parser(lex);
        Numbers num = (Numbers) p.parse();
        assertEquals(5,num.getValue(),0);
    
    }
    @Test
    public void TestAVariable(){
        //parsing a variable
        Lexer lex = new Lexer("(x)");
        Parser p = new Parser(lex);
        VAR var = (VAR) p.parse();
        assertEquals("x",var.getValue());
    }

    @Test
    public void TestSimpleMultiplying(){
        // parsing a simple multiplication
        Lexer lex = new Lexer("(x*y)");
        Parser p = new Parser(lex);
        Expression testMul = p.parse();
        VAR var = (VAR) testMul.getLeft();
        VAR var2 = (VAR) testMul.getRight();
        assertEquals("x", var.value);
        assertEquals("y", var2.value);
        assertEquals("differentiator.Multiply",testMul.getClass().getName());

    }
    
    @Test
    public void TestBasicSum(){
        //parsing a sum
        Lexer lex = new Lexer("(3+y)");
        Parser p = new Parser(lex);
        Expression k = p.parse();
        Numbers num = (Numbers) k.getLeft();
        VAR var2 = (VAR) k.getRight();
        assertEquals(3.0,num.value,0);
        assertEquals("y",var2.value);
        assertEquals("differentiator.Sum",k.getClass().getName());}

    
    @Test
    public void TwoSums(){
        //Test for a sum of a sum and a variable
        Lexer lex = new Lexer("((3+y)+x)");
        Parser p = new Parser(lex);
        Expression k = p.parse();
        VAR var = (VAR) k.getLeft().getRight();
        Numbers num = (Numbers) k.getLeft().getLeft();
        VAR var3 = (VAR) k.getRight();
        assertEquals("y",var.getValue());
        assertEquals(3,num.getValue(),0);
        assertEquals("x",var3.getValue());
    }
    @Test
    public void Complicated(){
        //Test for a combination of multiplication and sum
        Lexer lex = new Lexer("((3+y)*(x*4))");
        Parser p = new Parser(lex);
        Expression k = p.parse();
        Numbers num = (Numbers) k.getRight().getRight();
        VAR var = (VAR) k.getRight().getLeft();
        Numbers num2 = (Numbers) k.getLeft().getLeft();
        VAR var2 = (VAR) k.getLeft().getRight();
        assertEquals(4,num.getValue(),0);
        assertEquals("x",var.getValue());
        assertEquals("y",var2.getValue());
        assertEquals(3,num2.getValue(),0);
    }
    
    //invalid tests
    @Test(expected=RuntimeException.class)
  //Test an invalid token
  public void invalidClose() {
        Lexer lex = new Lexer(")");
        Parser p = new Parser(lex);
        Expression k = p.parse();
        }
      
    @Test(expected=RuntimeException.class)
  //Test an invalid wrong token ^
  public void InvalidToken() {
        Lexer lex = new Lexer("(3^x)");
        Parser p = new Parser(lex);
        Expression k = p.parse();
    }
}
