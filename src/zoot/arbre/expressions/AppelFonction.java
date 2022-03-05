package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;

public class AppelFonction extends ArbreAbstrait {
    private String idf;

    protected AppelFonction(int n, String idf) {
        super(n);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
