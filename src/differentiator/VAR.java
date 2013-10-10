package differentiator;

import differentiator.Token.Type;

public class VAR implements Expression  {
    //fixed based on a code review comment 
     String value;
    private Type type;
    public VAR(String valToEval, Type typeVar) {
        value = valToEval;
        type = typeVar;
    }
    // I prefer to keep all of my override declarations, I don't see any harm in them, they will guarantee
    // that all of the method will be right for this class
    @Override
    public Type getType(){
        return type;
    }
    @Override
    public Expression getLeft() {
        throw new UnsupportedOperationException("Variable does not have left");
    }

    @Override
    public Expression getRight() {
        throw new UnsupportedOperationException("Variable does not have right");
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    //fixed based on a code review comment 
    public String toString(){
        return value;
    }
    public Object accept(Visitor visitor, String valToDiff) {
        return visitor.visit(this,valToDiff);
    }

}


