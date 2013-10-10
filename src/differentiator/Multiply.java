package differentiator;

import differentiator.Token.Type;

public class Multiply implements Expression {
    // fixed privacy base on a code review comment 
    private Expression right, left;
    private Type type;
    // fixed variables names based on a code review comment
    public Multiply(Expression sideLeft, Expression sideRight, Type MulType) {
        this.left = sideLeft;
        this.right = sideRight;
        type= MulType;
    }

    @Override
    public Expression getLeft() {
        return this.left;
    }

    @Override
    public Expression getRight() {
        return this.right;
    }
    
    @Override
    public String toString(){
        String answer;
        answer= "("+left.toString()+"*"+right.toString()+")";
        return answer;
    }

    @Override
    public Type getType() {
        return type;
    }
    // my function for nums will take care of the extra string, I prefer to have all of my visitor methods 
    // similar to one another.
    public Object accept(Visitor visitor, String valToDiff) {
        return visitor.visit(this,valToDiff);
    }



}
