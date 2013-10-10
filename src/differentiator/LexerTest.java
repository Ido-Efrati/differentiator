package differentiator;
import static org.junit.Assert.*;

import org.junit.Test;

import differentiator.Token.Type;

public class LexerTest {
@Test

//All of the test have comments to make them easier to understand
//Valid test: variable and numbers, both sum and multiplication with integers ,floats and different vars
// invalid: string that is not in my regex 

//Test sum of both variables and numbers
public void sumOfVariablesandNums() {
    Lexer lex = new Lexer("(foo+bar+3+100+22+x)");
    Token tok;
    tok = lex.next();
    assertEquals(Type.OPEN_PAREN,tok.getType());
    assertEquals(tok.getValue(), "(");

    tok = lex.next();
    assertEquals(Type.VARS,tok.getType());
    assertEquals(tok.getValue(), "foo");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.VARS);
    assertEquals(tok.getValue(), "bar");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.NUMS);
    assertEquals(tok.getValue(), "3");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.NUMS);
    assertEquals(tok.getValue(), "100");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.NUMS);
    assertEquals(tok.getValue(), "22");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.VARS);
    assertEquals(tok.getValue(), "x");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.CLOSE_PAREN);
        }
@Test
//Test for multiplication of variables and numbers
public void MulOfVariablesandNums() {
  Lexer lex = new Lexer("(foo*bar*3*x)");
  Token tok;

  tok = lex.next();
  assertEquals(Type.OPEN_PAREN,tok.getType());
  assertEquals(tok.getValue(), "(");

  
    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "foo");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "bar");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.NUMS);
    assertEquals(tok.getValue(), "3");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");

    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "x");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.CLOSE_PAREN);

}

@Test
//Test for multiplication of variables and numbers
public void FloatTest() {
  Lexer lex = new Lexer("(1.2+3*4.1)");
  Token tok;
  
  tok = lex.next();
  assertEquals(Type.OPEN_PAREN,tok.getType());
  assertEquals(tok.getValue(), "(");

  tok = lex.next();
  assertEquals(tok.getType(),Type.NUMS);
  assertEquals(tok.getValue(), "1.2");
  
  tok = lex.next();
  assertEquals(tok.getType(),Type.SUM);
  assertEquals(tok.getValue(), "+");
  
  tok = lex.next();
  assertEquals(tok.getType(),Type.NUMS);
  assertEquals(tok.getValue(), "3");
  
  tok = lex.next();
  assertEquals(tok.getType(),Type.MUL);
  assertEquals(tok.getValue(), "*");
  
  tok = lex.next();
  assertEquals(tok.getType(),Type.NUMS);
  assertEquals(tok.getValue(), "4.1");
  
  tok = lex.next();
  assertEquals(Type.CLOSE_PAREN,tok.getType());
  assertEquals(tok.getValue(), ")");

}

  
@Test
//Test a combnation of mul , var and paren
public void CombinationOfMulVar() {
    Lexer lex = new Lexer("((x+3.2)*(y*4)+((x+1)+(z+3)*(bar)))");
    Token tok;

    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "x");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.NUMS);
    assertEquals(tok.getValue(), "3.2");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");

    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");

    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "y");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");

    tok = lex.next();
    assertEquals(tok.getType(),Type.NUMS);
    assertEquals(tok.getValue(), "4");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");

    
    tok = lex.next();
    assertEquals(tok.getType(),Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "x");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");

    tok = lex.next();
    assertEquals(tok.getType(),Type.NUMS);
    assertEquals(tok.getValue(), "1");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");
    
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");

    
    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "z");
    
    tok = lex.next();
    assertEquals(tok.getType(), Type.SUM);
    assertEquals(tok.getValue(), "+");

    tok = lex.next();
    assertEquals(tok.getType(),Type.NUMS);
    assertEquals(tok.getValue(), "3");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");

    
    tok = lex.next();
    assertEquals(tok.getType(), Type.MUL);
    assertEquals(tok.getValue(), "*");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.OPEN_PAREN);
    assertEquals(tok.getValue(), "(");

    tok = lex.next();
    assertEquals(tok.getType(),Type.VARS);
    assertEquals(tok.getValue(), "bar");
    
    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");

    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");

    tok = lex.next();
    assertEquals(tok.getType(),Type.CLOSE_PAREN);
    assertEquals(tok.getValue(), ")");
}

@Test(expected=RuntimeException.class)
//Test an invalid token
public void invalidToken() {
  Lexer lex = new Lexer("^");
  Token tok;
  tok = lex.next();
}

@Test(expected=RuntimeException.class)
//Test an invalid f o o based on a code review comment
public void spaces() {
Lexer lex = new Lexer("f o o");
Token tok;
tok = lex.next();
}
// as for the comment about open and close paren. I am going to keep those tokens because my code 
// deisgn was based on those tokens.
    }


