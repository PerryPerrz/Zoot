package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;

public class Fonction extends ArbreAbstrait {
    private final String idf;
    private final ArbreAbstrait bloc;

    public Fonction(int n, String idf, ArbreAbstrait bloc) {
        super(n);
        this.idf = idf;
        this.bloc = bloc;
    }

    @Override
    public void verifier() {
        this.bloc.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n#Déclaration de la fonction ").append(this.idf).append("\n");
        sb.append(this.idf).append(": \n").append(this.bloc.toMIPS());
        sb.append("\tjr $ra"); //On revient à l'endroit ou l'appel de fonction a été effectué
        return sb.toString();
    }
}
