package zoot.arbre.expressions.operateurs.unaires;

import zoot.arbre.expressions.Expression;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public class Non extends Expression {
    private final Expression exp;
    private final int numCarac;

    /**
     * Constructeur de la classe Expression.
     *
     * @param n numéro de ligne
     */
    public Non(int n, Expression exp, int numCarac) {
        super(n);
        this.exp = exp;
        this.numCarac = numCarac;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (!exp.getType().equals("booleen"))
            StockageErreurs.getInstance().ajouter(new Erreur("Appliquer un 'non' sur une expression autre que booléenne n'est pas autorisé !", noLigne));
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toMIPS(registres));
        sb.append("\n# Initialiser $t8 avec la valeur faux\n");
        sb.append("\tla $t8, faux\n");
        sb.append("\tbeq $t8, $v0, Sinon").append(noLigne).append("Car").append(numCarac).append("\n");
        sb.append("\tla $v0, faux\n");
        sb.append("\tb FinSi").append(noLigne).append("Car").append(numCarac).append("\n");
        sb.append("Sinon").append(noLigne).append("Car").append(numCarac).append(":").append("\n");
        sb.append("\tla $v0, vrai\n");
        sb.append("FinSi").append(noLigne).append("Car").append(numCarac).append(":\n");
        return sb.toString();
    }

    @Override
    public String getType() {
        return "booleen";
    }

    @Override
    public int getNbErchov() {
        return exp.getNbErchov();
    }

    @Override
    public String toString() {
        return "non" + exp.toString();
    }
}
