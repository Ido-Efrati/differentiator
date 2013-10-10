package differentiator;

import differentiator.Token.Type;

public interface Expression {
    /**
     * Get left get the left subtree.
     * @return The left subtree.
     * Get right get the right subtree.
     * @return The right subtree.
     * evaluate the expression.
     * @return The a new evaluated expression.
     * To string
     * @return the a String
     */
       public Expression getLeft();
       public Expression getRight();
       public Type getType();
       public String toString();
       public Object accept(Visitor v,String str);
   }


