package differentiator;
import static org.junit.Assert.*;

import org.junit.Test;

public class DifferentiaorTest {
    
    @Test
    //This test will check for a number in several parens
    public void justaNestedNum() {
        String number = "((((55))))";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("0", answer); 
    }

    @Test
    //this test will check for a var with a new expression to evaluate
    public void justaVar() {
        String number = "(y)";
        String param = "y";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("1", answer); 
    }
    
    @Test
    public void justaVarOtherParam() {
        //This test check for a var with a different exrpession. we should get zero 
        String number = "(y)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("0", answer); 
    }
    
    @Test
    public void Sum() {
        //This test check a sum
        String number = "(x+y)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("(1+0)", answer); 
    }
    
    @Test
    public void mul() {
        //This test check a basic multiplication
        String number = "(x*y)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("((1*y)+(0*x))", answer); 
    }

    @Test 
    public void complexFloatSumAndMul() {
        //This test check for both sum, multiplication and floats.
        String number = "((y*   x)+(5.3*   x))";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("(((0*x)+(1*y))+((0*x)+(1*5.3)))", answer); 
    }
    
    @Test 
    public void complex1() {
        //check for a lot of spaces in my expression
        String number = "((2*x    )+     (   y*x     ))";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("(((0*x)+(1*2.0))+((0*x)+(1*y)))", answer); 
    }

    
    @Test 
    public void complex2() {
        //Test from the pset - a lot of parens 
        String number = "((4 + (3 * x)) + (((2 * x) * x) + ((1 * x) * (x * x))))";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
        assertEquals("((0+((0*x)+(1*3.0)))+(((((0*x)+(1*2.0))*x)+(1*(2.0*x)))+((((0*x)+(1*1.0))*(x*x))+(((1*x)+(1*x))*(1.0*x)))))", answer); 
    }

    //Invalid cases - all should fail 
    @Test(expected=RuntimeException.class)
    public void InvalidInputMissingClose() {
        String number = "(5";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }

    @Test(expected=RuntimeException.class)
    public void InvalidInputMissingOpen() {
        String number = "5)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }

    @Test(expected=RuntimeException.class)
    public void InvalidInputNoOpenOrClose() {
        String number = "5";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }

    @Test(expected=RuntimeException.class)
    public void NotTheSameNumber() {
        String number = "((5)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    
    
    @Test(expected=RuntimeException.class)
    public void notOp_combineNoSpace() {
        String number = "(3x)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    
// Added test for all of the cases I failed during my beta tester for regression 
    @Test(expected=RuntimeException.class)
    public void ExtraRparen() {
        String number = "(x))";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    
    
    @Test(expected=RuntimeException.class)
    public void MissingOperandVars() {
        String number = "(x x)";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    

    @Test(expected=RuntimeException.class)
    public void trillingVar() {
        String number = "(x+x)x";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    
    //added a test based on a code review comment
    @Test(expected=RuntimeException.class)
    public void missingClose() {
        String number = "(x+3";
        String param = "x";
        Differentiator e = new Differentiator();
        String answer =e.evaluate(number, param);   
    }    

}
