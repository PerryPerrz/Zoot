package zoot.arbre.expressions;

public abstract class Constante extends Expression {

    protected String cste;

    protected Constante(String texte, int n) {
        super(n);
        cste = texte;
    }

    @Override
    public void verifier() {
        //Il n'y a rien à vérifier dans cette classe
    }

    @Override
    public String toString() {
        return cste;
    }

    @Override
    public boolean estUneVariable() {
        return false;
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return false;
    }
}
