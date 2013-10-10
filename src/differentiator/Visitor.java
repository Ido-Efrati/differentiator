package differentiator;

//create a visitor interface that will allow to implement the visitor pattern
public interface Visitor {
        public Expression visit(Expression e,String valToDiff);
        public Expression visit(VAR v,String valToDiff);
        public Expression visit(Numbers n,String valToDiff);
        public Expression visit(Multiply m, String valToDiff);
        public Expression visit(Sum s, String valToDiff);
    }  

