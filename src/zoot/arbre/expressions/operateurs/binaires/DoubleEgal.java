package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;

public class DoubleEgal extends BinaireMixte {
    public DoubleEgal(int n, Expression eGauche, Expression eDroite, int numCarac) {
        super(n, eGauche, eDroite, numCarac);
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        sb.append(eGauche.toMIPS(registres));
        if (registres.length != 1) {
            sb.append("\tmove ").append(registres[1]).append(", ").append(registres[0]).append("\n");
            sb.append(eDroite.toMIPS(this.supprRegistreInutile(1, registres)));
            sb.append("\tbeq ").append(registres[1]).append(", ").append(registres[0]).append(", Sinon").append(noLigne).append("Car").append(numCarac).append("\n");
            sb.append("\tla $v0, faux\n");
            sb.append("\tb FinSi").append(noLigne).append("Car").append(numCarac).append("\n");
            sb.append("Sinon").append(noLigne).append("Car").append(numCarac).append(":").append("\n");
            sb.append("\tla $v0, vrai\n");
            sb.append("FinSi").append(noLigne).append("Car").append(numCarac).append(":\n");
        } else {
            //TODO : utiliser la pile
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return eGauche.toString() + "==" + eDroite.toString();
    }
}
