package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

public class Affect extends Instruction {

    protected Idf idf;
    protected Expression exp;

    public Affect(Idf i, Expression e, int n) {
        super(n);
        idf = i;
        exp = e;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ");
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
