package differentiator;

import differentiator.Token.Type;

public class Numbers implements Expression {
    float value;
    //Expression left;
    Type type;
    public Numbers(String valToEval, Type numType) {
        value = new Float(valToEval); 
        type= numType;
    }

    // added text comment in the exception
    @Override
    public Expression getLeft() {
        throw new UnsupportedOperationException("number does not have left");}

    @Override
    public Expression getRight() {
        throw new UnsupportedOperationException("number does not have right");
        
    }
    public float getValue(){
        return value;
    }
    
    @Override
    public String toString(){
        String answer;
        answer= String.valueOf(value);
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
