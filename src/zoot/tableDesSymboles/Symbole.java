package zoot.tableDesSymboles;

/**
 * Classe Symbole.
 */
public class Symbole {
    private String type;
    private final int noBloc;
    private int deplacement;
    private String etiquette;

    /**
     * Constructeur de la classe Symbole.
     *
     * @param type, type de la variable
     */
    public Symbole(String type, int noBloc) {
        this.type = type;
        this.noBloc = noBloc;
    }

    /**
     * Fonction qui retourne le type d'une variable.
     *
     * @return le type de la variable
     */
    public String getType() {
        return type;
    }

    /**
     * Procédure qui définit le type de la variable.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Fonction qui retourne la position de la variable dans la pile.
     *
     * @return
     */
    public int getDeplacement() {
        return deplacement;
    }

    /**
     * Procédure qui définit la position de la variable dans la pile.
     *
     * @param deplacement
     */
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    /**
     * Fonction qui retourne le numéro de bloc
     *
     * @return le numéro de bloc
     */
    public int getNoBloc() {
        return noBloc;
    }

    /**
     * Fonction qui retourne l'étiquette d'un bloc.
     *
     * @return l'étiquette d'un bloc
     */
    public String getEtiquette() {
        return etiquette;
    }

    /**
     * Procédure qui définit l'étiquette d'un bloc
     *
     * @param etiquette
     */
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
}
