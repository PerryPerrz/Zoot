package zoot.arbre.expressions.operateurs.unaires;

import zoot.arbre.expressions.Expression;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public class Non extends Expression {
    private final Expression exp;

    /**
     * Constructeur de la classe Expression.
     *
     * @param n numéro de ligne
     */
    public Non(int n, Expression exp) {
        super(n);
        this.exp = exp;
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
        //TODO : inverser la valeur du bool
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
