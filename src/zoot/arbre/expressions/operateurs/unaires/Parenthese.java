package zoot.arbre.expressions.operateurs.unaires;

import zoot.arbre.expressions.Expression;

public class Parenthese extends Expression {
    private final Expression exp;

    /**
     * Constructeur de la classe Expression.
     *
     * @param n numéro de ligne
     */
    public Parenthese(int n, Expression exp) {
        super(n);
        this.exp = exp;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS(String... registres) {
        return exp.toMIPS(registres);
    }

    @Override
    public String getType() {
        return exp.getType();
    }

    @Override
    public boolean estUneVariable() {
        return false;//TODO : vérif si ça pose pas de pb de pas relayer la rep de l'exp.
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return false;//TODO : vérif si ça pose pas de pb de pas relayer la rep de l'exp.
    }

    @Override
    public String toString() {
        return "(" + exp.toString() + ")";
    }

    @Override
    public int getNbErchov() {
        return exp.getNbErchov();
    }
}
