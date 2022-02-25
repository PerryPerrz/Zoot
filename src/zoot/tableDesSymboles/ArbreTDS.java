package zoot.tableDesSymboles;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe ArbreTDS qui gère les différents blocs d'istructions.
 */
public class ArbreTDS {
    private final int id;
    private final HashMap<Entree, Symbole> bloc;
    private final ArrayList<ArbreTDS> listeDeFils;
    private final ArbreTDS pere;

    public ArbreTDS(int id, ArbreTDS pere) {
        this.id = id;
        this.bloc = new HashMap<Entree, Symbole>();
        this.listeDeFils = new ArrayList<>();
        this.pere = pere;
    }

    /**
     * Procédure qui ajoute un identifiant ainsi que son symbole à la table des symboles.
     *
     * @param e définition du symbole. Il contient le type et le nom d'une variable ou d'une fonction.
     * @param s le type de la variable
     * @throws DoubleDeclarationException Exception étant appelée lorsque l'utilisateur veut déclarer une variable déjà inscrite dans la table des symboles.
     */
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        if (this.bloc.containsKey(e)) {
            throw new DoubleDeclarationException("Le symbole " + e.getIdf() + " ne peut pas être ajouté deux fois dans la table des symboles !");
        }
        s.setDeplacement(this.getTailleZoneVariables());
        this.bloc.put(e, s);
    }

    /**
     * Fonction qui identifie une variable dans la table des symboles et retourne son symbole correspondant.
     *
     * @param e définition du symbole. Il contient le type et le nom d'une variable ou d'une fonction.
     * @return le symbole de la variable
     * @throws VariableNonDeclareeException Exception étant appelée lorsque l'utilisateur recherche une variable n'étant pas enregistrée dans la table des symboles.
     */
    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        if (!this.bloc.containsKey(e)) {
            throw new VariableNonDeclareeException("Le symbole " + e + " n'existe pas dans la table des symboles !");
        }
        return this.bloc.get(e);
    }

    /**
     * Fonction qui retourne la taille de la zone contenant toutes les variables définies dans la table des symboles.
     *
     * @return la taille en octets.
     */
    public int getTailleZoneVariables() {
        //On définit la taille d'un entier et la taille d'un booléen à 4 octets. (on descend dans la pile donc -4)
        return this.bloc.size() * -4;
    }

    /**
     * Fonction qui retourne la liste des fils.
     *
     * @return la liste des fils
     */
    public ArrayList<ArbreTDS> getListeDeFils() {
        return listeDeFils;
    }

    /**
     * Fonction qui retourne l'id d'un arbre.
     *
     * @return l'id d'un arbre
     */
    public int getId() {
        return id;
    }

    /**
     * Fonction qui retourne le père d'un arbre.
     *
     * @return le père d'un arbre
     */
    public ArbreTDS getPere() {
        return pere;
    }
}
