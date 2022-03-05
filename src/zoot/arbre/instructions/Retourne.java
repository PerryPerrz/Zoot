package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Retourne extends Instruction{
    private Expression e;

    protected Retourne(int n, Expression e) {
        super(n);
        this.e=e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
