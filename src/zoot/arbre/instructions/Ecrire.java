package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp;

    public Ecrire(Expression e, int n) {
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ");
    }

    @Override
    public String toMIPS() {
        return "\tli $a0 , " + exp.toMIPS() + "\n" +
                "\tli $v0 , 1\n" +
                "\tsyscall\n";
    }

}
