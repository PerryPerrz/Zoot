package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;
import zoot.tableDesSymboles.TDS;

/**
 * Classe Fonction.
 */
public class Fonction extends ArbreAbstrait {
    private final String idf;
    private final ArbreAbstrait bloc;
    private final String[] typeParam;

    /**
     * Constructeur de la classe Fonction.
     *
     * @param n    le numéro de ligne
     * @param idf  l'identifiant
     * @param bloc le bloc d'instruction
     */
    public Fonction(int n, String idf, ArbreAbstrait bloc, String typeParams) {
        super(n);
        this.idf = idf;
        this.bloc = bloc;
        this.typeParam = typeParams.split(",");
    }

    @Override
    public void verifier() {
        TDS.getInstance().entreeBlocVerif();
        this.bloc.verifier();
        TDS.getInstance().sortieBloc();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n#Déclaration de la fonction ").append(this.idf).append("\n");
        sb.append(this.idf).append(this.typeParam.length).append(": \n").append(this.bloc.toMIPS()); //On ajoute le nombre de paramètre à l'étiquette car c'est la façon dont on différencie nos fonctions.
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

    public String[] getTypeParam() {
        return typeParam;
    }

    public int getNbParams() {
        return typeParam.length;
    }
}
