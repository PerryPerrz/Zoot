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
    public Et(int n, Expression eGauche, Expression eDroite, int numCarac) {
        super(n, eGauche, eDroite, numCarac);
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        sb.append(eGauche.toMIPS(registres));
        if (registres.length != 1) {
            sb.append("\n# Initialiser $t8 avec la valeur faux\n");
            sb.append("\tla $t8, faux\n");
            sb.append("\tbeq $t8, ").append(registres[0]).append(", Sinon").append(noLigne).append("Car").append(numCarac).append("\n");
                sb.append(eDroite.toMIPS(registres));
                sb.append("\tbeq $t8, ").append(registres[0]).append(", SinonImbrique").append(noLigne).append("Car").append(numCarac).append("\n");
                    sb.append("\tla $v0, vrai\n");
                sb.append("\tb FinSiImbrique").append(noLigne).append("Car").append(numCarac).append("\n");
                sb.append("SinonImbrique").append(noLigne).append("Car").append(numCarac).append(":").append("\n");
                    sb.append("\tla $v0, faux\n");
                sb.append("FinSiImbrique").append(noLigne).append("Car").append(numCarac).append(":\n");
            sb.append("\tb FinSi").append(noLigne).append("Car").append(numCarac).append("\n");
            sb.append("Sinon").append(noLigne).append("Car").append(numCarac).append(":").append("\n");
                sb.append("\tla $v0, faux\n");
            sb.append("FinSi").append(noLigne).append("Car").append(numCarac).append(":\n");
        } else {
            //TODO : utiliser la pile
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return eGauche.toString() + " et " + eDroite.toString();
    }
}
