package zoot.tableDesSymboles;

/**
 * Classe Entrée.
 */
public class Entree {
    private final String idf;
    private final String type; //Vaut soit "variable" soit "fonction"
    private final String typeParam; //Type des paramètres

    /**
     * Constructeur de la classe Entrée.
     *
     * @param idf  l'identifiant
     * @param type le type de l'entrée
     */
    public Entree(String idf, String type) {
        this.idf = idf;
        this.type = type;
        this.typeParam = "";
    }

    public Entree(String idf, String type, String typeParam) {
        this.idf = idf;
        this.type = type;
        this.typeParam = typeParam;
    }

    /**
     * Fonction qui retourne l'identifiant de l'entrée.
     *
     * @return l'identifiant de l'entrée
     */
    public String getIdf() {
        return idf;
    }

    /**
     * Fonction qui retourne le type de l'entrée.
     *
     * @return le type de l'entrée.
     */
    public String getType() {
        return type;
    }

    public String getTypeParam() {
        return this.typeParam;
    }
}
