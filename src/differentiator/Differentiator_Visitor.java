package differentiator;

import differentiator.Token.Type;

public class Differentiator_Visitor implements Visitor {

    //for a variable that matches the expression we will return a 1
    // else we will return a zero
    public Expression visit(VAR v, String valToDiff) {
        if(v.getValue().equals(valToDiff)){
            // addressing a code review: I can just return a var, but i prefer to create it first
            // this will make it a lot easier to understand for someone who is not farmiliar with my code
            VAR one = new VAR("1",Type.VARS);
            return one;
        }
        VAR zero = new VAR("0",Type.VARS);
        return zero;
    }

    //for every number we will return a 0
    public Expression visit(Numbers n, String valToDiff) {
        VAR zero = new VAR("0",Type.VARS);
        return zero;
    }

    //recursively calling the left and right in order to evaluate them at the end we will return a sum
    // of the multiplications 
    public Expression visit(Multiply m, String valToDiff) {
        Expression left_side= (Expression) this.visit(m.getLeft(),valToDiff);
        Expression right_side= (Expression) this.visit(m.getRight(), valToDiff);
        Multiply first = new Multiply(left_side,m.getRight(),Type.MUL);
        Multiply second = new Multiply(right_side,m.getLeft(),Type.MUL);
        Sum finalValue = new Sum(first,second,Type.SUM);

        return finalValue;
    }

    // we will return a sum of the left and the right after we recursively visited them 
    public Expression visit(Sum s, String valToDiff) {
        Expression left_side= (Expression) this.visit(s.getLeft(),valToDiff);
        Expression right_side= (Expression) this.visit(s.getRight(), valToDiff);
        Sum finalSum = new Sum(left_side,right_side,Type.SUM);
        return finalSum;
    }

    //general method to all the possible visits 
    //the following if else statements are short and easy to understand, while switch case
    // can work perfectly fine here. I don't think the following logic is too complicated to understand
    // thus I don't think an switch case is mandatory here
    @Override
    public Expression visit(Expression e, String valToDiff) {
        Type a = e.getType();
        if(a.equals(Type.SUM)){
            return this.visit((Sum) e, valToDiff);
        }
        else if(a.equals(Type.MUL)){
            return this.visit((Multiply) e, valToDiff);
        }
        else if(a.equals(Type.NUMS)){
            return this.visit((Numbers) e, valToDiff);
        }
        else if(a.equals(Type.VARS)){
            return this.visit((VAR) e, valToDiff);
        }
        throw new RuntimeException("did not match any of the visitors");
    }

}
