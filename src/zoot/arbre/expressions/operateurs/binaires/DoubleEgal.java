package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;

public class DoubleEgal extends BinaireMixte{
    public DoubleEgal(int n, Expression eGauche, Expression eDroite) {
        super(n, eGauche, eDroite);
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        //TODO : Comparer
        return sb.toString();
    }

    @Override
    public String toString() {
        return eGauche.toString() + "==" + eDroite.toString();
    }
}
