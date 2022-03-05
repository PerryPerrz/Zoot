package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected Expression(int n) {
        super(n);
    }

    /**
     * Fonction qui retourne le type de l'expression
     *
     * @return une chaîne de caractère correspondant au type de l'expression ("Entier" ou "Booleen").
     */
    public abstract String getType();

    public abstract boolean estUneVariable();

    public abstract boolean estUnAppelDeFonction();

    public String getSignatureFonction() {
        return null;
    }
}
