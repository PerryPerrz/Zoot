package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;

/**
 * Classe Ou.
 */
public class Ou extends BinaireBooleen {
    /**
     * Constructeur de la classe Expression.
     *
     * @param n       numéro de ligne
     * @param eGauche expression gauche
     * @param eDroite expression droite
     */
    public Ou(int n, Expression eGauche, Expression eDroite) {
        super(n, eGauche, eDroite);
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        sb.append(eGauche.toMIPS(registres));

        return sb.toString();
    }

    @Override
    public String toString() {
        return eGauche.toString() + " ou " + eDroite.toString();
    }
}
