package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

/**
 * Classe abstraite Expression.
 */
public abstract class Expression extends ArbreAbstrait {
    /**
     * Constructeur de la classe Expression.
     *
     * @param n numéro de ligne
     */
    protected Expression(int n) {
        super(n);
    }

    /**
     * Fonction qui retourne le type de l'expression
     *
     * @return une chaîne de caractère correspondant au type de l'expression ("Entier" ou "Booleen").
     */
    public abstract String getType();

    /**
     * Fonction qui retourne vrai si l'expression regardée est une variable.
     *
     * @return vrai si l'expression regardée est une variable.
     */
    public abstract boolean estUneVariable();

    /**
     * Fonction qui retourne vrai si l'expression regardée est un appel de fonction.
     *
     * @return vrai si l'expression regardée est un appel de fonction.
     */
    public abstract boolean estUnAppelDeFonction();

    /**
     * Fonction qui retourne la signature d'une fonction.
     *
     * @return la signature d'une fonction.
     */
    public String getSignatureFonction() {
        return null;
    }

    /**
     * Fonction qui retourne le type de paramètre.
     *
     * @return un string, le type de paramètre
     */
    public String getTypeParam() {
        return "";
    }
}
