package zoot.arbre.instructions;

import zoot.arbre.expressions.Idf;

public class Lire extends Instruction {

    protected Idf idf ;

    public Lire (Idf i, int n) {
        super(n) ;
        idf = i ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
