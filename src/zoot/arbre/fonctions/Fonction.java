package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;

/**
 * Classe Fonction.
 */
public class Fonction extends ArbreAbstrait {
    private final String idf;
    private final ArbreAbstrait bloc;

    /**
     * Constructeur de la classe Fonction.
     *
     * @param n    le numéro de ligne
     * @param idf  l'identifiant
     * @param bloc le bloc d'instruction
     */
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
        sb.append("\n\n#Déclaration de la fonction ").append(this.idf).append("\n");
        sb.append(this.idf).append(": \n").append(this.bloc.toMIPS());
        sb.append("\tjr $ra"); //On revient à l'endroit ou l'appel de fonction a été effectué
        return sb.toString();
    }

    /**
     * Fonction qui retourne l'identifiant d'une fonction.
     *
     * @return l'identifiant d'une fonction.
     */
    public String getIdf() {
        return idf;
    }
}
