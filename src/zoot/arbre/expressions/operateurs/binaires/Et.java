package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;

/**
 * Classe Et.
 */
public class Et extends BinaireBooleen {
    /**
     * Constructeur de la classe Et.
     *
     * @param n       numéro de ligne
     * @param eGauche expression gauche
     * @param eDroite expression droite
     */
    public Et(int n, Expression eGauche, Expression eDroite) {
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
        return eGauche.toString() + " et " + eDroite.toString();
    }
}
