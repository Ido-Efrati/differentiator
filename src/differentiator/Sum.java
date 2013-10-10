package differentiator;

import differentiator.Token.Type;

public class Sum implements Expression{
    //fixed privacy based on a comment 
    private Expression left, right;
    private Type type;

    public Sum(Expression Left, Expression Right, Type VarType) {
        this.left = Left;
        this.right = Right;
        type= VarType;
    }
    // I prefer to keep all of my override declarations, I don't see any harm in them, they will guarantee
    // that all of the method will be right for this class
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
        answer= "("+ left.toString()+"+"+right.toString()+")";
        return answer;
    }

    @Override
    public Type getType() {
        return type;
    }
    
    public Object accept(Visitor visitor, String valToDiff) {
        return visitor.visit(this,valToDiff);
    }

}
