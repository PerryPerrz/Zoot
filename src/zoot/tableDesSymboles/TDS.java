package zoot.tableDesSymboles;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.HashMap;

/**
 * Singleton TDS représentant la table des symboles de zoot.
 */
public class TDS {
    private final HashMap<String, Symbole> tableDesSymboles;

    /**
     * Constructeur privé du singleton TDS.
     */
    private TDS() {
        this.tableDesSymboles = new HashMap<>();
    }

    private static final TDS instance = new TDS();

    /**
     * Fonction qui retourne une instance de la classe TDS.
     *
     * @return une instance de la classe TDS.
     */
    public static TDS getInstance() {
        return instance;
    }

    /**
     * Procédure qui ajoute un identifiant ainsi que son symbole à la table des symboles.
     *
     * @param idf l'identifiant de la variable
     * @param s   le type de la variable
     * @throws DoubleDeclarationException Exception étant appelée lorsque l'utilisateur veut déclarer une variable déjà inscrite dans la table des symboles.
     */
    public void ajouter(String idf, Symbole s) throws DoubleDeclarationException {
        if (this.tableDesSymboles.containsKey(idf)) {
            throw new DoubleDeclarationException("Le symbole " + idf + " ne peut pas être ajouté deux fois dans la table des symboles !");
        }
        s.setDeplacement(this.getTailleZoneVariables());
        this.tableDesSymboles.put(idf, s);
    }

    /**
     * Fonction qui identifie une variable dans la table des symboles et retourne son symbole correspondant.
     *
     * @param idf l'identifiant de la variable
     * @return le symbole de la variable
     * @throws VariableNonDeclareeException Exception étant appelée lorsque l'utilisateur recherche une variable n'étant pas enregistrée dans la table des symboles.
     */
    public Symbole identifier(String idf) throws VariableNonDeclareeException {
        if (!this.tableDesSymboles.containsKey(idf)) {
            throw new VariableNonDeclareeException("Le symbole " + idf + " n'existe pas dans la table des symboles !");
        }
        return this.tableDesSymboles.get(idf);
    }

    /**
     * Fonction qui retourne la taille de la zone contenant toutes les variables définies dans la table des symboles.
     *
     * @return la taille en octets.
     */
    public int getTailleZoneVariables() {
        //On définit la taille d'un entier et la taille d'un booléen à 4 octets. (on descend dans la pile donc -4)
        return this.tableDesSymboles.size() * -4;
    }
}
