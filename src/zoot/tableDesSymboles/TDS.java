package zoot.tableDesSymboles;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton TDS représentant la table des symboles de zoot.
 */
public class TDS {
    private final HashMap<Entree, Symbole> tableDesSymboles;

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
     * @param e l'entrée
     * @param s le type de la variable
     * @throws DoubleDeclarationException Exception étant appelée lorsque l'utilisateur veut déclarer une variable déjà inscrite dans la table des symboles.
     */
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        if (this.tableDesSymboles.containsKey(e)) {
            throw new DoubleDeclarationException("Le symbole " + e.getIdf() + " ne peut pas être ajouté deux fois dans la table des symboles !");
        }
        s.setDeplacement(this.getTailleZoneVariables());
        this.tableDesSymboles.put(e, s);
    }

    /**
     * Fonction qui identifie une variable dans la table des symboles et retourne son symbole correspondant.
     *
     * @param e l'entrée
     * @return le symbole de la variable
     * @throws VariableNonDeclareeException Exception étant appelée lorsque l'utilisateur recherche une variable n'étant pas enregistrée dans la table des symboles.
     */
    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        //TODO Si erreur : vérif si il ne faut pas throw une erreur quand se n'est pas une variable ou une fonction.
        Symbole s = null;
        for (Map.Entry<Entree, Symbole> elem : this.tableDesSymboles.entrySet()) {
            if (elem.getKey().getIdf().equals(e.getIdf())) {
                s = new Symbole(elem.getValue().getType());
                s.setDeplacement(elem.getValue().getDeplacement());
            }
        }
        if (s == null) { //TODO Changer erreur : peut être une fonction
            throw new VariableNonDeclareeException("Le symbole " + e.getIdf() + " n'existe pas dans la table des symboles !");
        }
        return s;
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
