package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;

public class Fonction extends ArbreAbstrait {
    private final String nom;
    private BlocDInstructions bloc;

    public Fonction(int n, String idf, BlocDInstructions bloc) {
        super(n);
        this.nom = idf;
        this.bloc = bloc;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        //TODO
        return null;
    }
}
