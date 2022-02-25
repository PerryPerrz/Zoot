package zoot.tableDesSymboles;

/**
 * Classe Entree
 */
public class Entree {
    private final String type; //type = "variable" | "fonction"
    private final String idf; //nom de la variable | fonction
    //Ajouter un attribut pour gérer le type des paramètre de la fonction

    /**
     * Constructeur de la classe Entree
     *
     * @param type "variable" ou "fonction"
     * @param idf  nom de la variable ou nom de la fonction
     */
    public Entree(String type, String idf) {
        this.type = type;
        this.idf = idf;
    }

    /**
     * Fonction qui retourne le type de l'entrée
     *
     * @return le type de l'entrée
     */
    public String getType() {
        return type;
    }

    /**
     * Fonction qui retourne l'identifiant de l'entrée
     *
     * @return l'identifiant de l'entrée
     */
    public String getIdf() {
        return idf;
    }
}
