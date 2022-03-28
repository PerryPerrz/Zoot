package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public abstract class BinaireBooleen extends Expression {
    protected Expression eGauche;
    protected Expression eDroite;

    /**
     * Constructeur de la classe Expression.
     *
     * @param n numéro de ligne
     */
    protected BinaireBooleen(int n, Expression eGauche, Expression eDroite) {
        super(n);
        this.eGauche = eGauche;
        this.eDroite = eDroite;
    }

    @Override
    public void verifier() {
        eGauche.verifier();
        eDroite.verifier();
        if (!eGauche.getType().equals("booleen") || !eDroite.getType().equals("booleen"))
            StockageErreurs.getInstance().ajouter(new Erreur("Une des expressions autour de l'opérateur binaire n'est pas un booléen !", noLigne));
    }

    @Override
    public String toMIPS(String... registres) {
        return null;
    }

    @Override
    public String getType() {
        return "booleen";
    }

    @Override
    public boolean estUneVariable() {
        return false;
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return false;
    }

    @Override
    public int getNbErchov() {
        return eGauche.getNbErchov() == eDroite.getNbErchov() ? eGauche.getNbErchov() + 1 : Math.max(eGauche.getNbErchov(), eDroite.getNbErchov());
    }
}
