package zoot.tableDesSymboles;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.EntreeNonDeclareeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton TDS représentant la table des symboles de zoot.
 */
public class TDS {
    //On fait une arrayList de tds (une pour le main, une par fonction).
    private final ArrayList<HashMap<Entree, Symbole>> tableDesSymboles;
    private int noBlocActuel = 0;
    private int noBlocPrec = 0;

    /**
     * Constructeur privé du singleton TDS.
     */
    private TDS() {
        this.tableDesSymboles = new ArrayList<>();
        this.tableDesSymboles.add(new HashMap<>()); //Ajout de la table des symboles représentant le bloc 0 (main)
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
        for (Map.Entry<Entree, Symbole> elem : this.tableDesSymboles.get(noBlocActuel).entrySet()) {
            if (elem.getKey().getIdf().equals(e.getIdf())) {
                throw new DoubleDeclarationException("Le symbole " + e.getIdf() + " ne peut pas être ajouté deux fois dans la table des symboles !");
            }
        }
        s.setDeplacement(this.getTailleZoneVariables());
        this.tableDesSymboles.get(noBlocActuel).put(e, s);
    }

    /**
     * Fonction qui identifie une variable dans la table des symboles et retourne son symbole correspondant.
     *
     * @param e l'entrée
     * @return le symbole de la variable
     * @throws EntreeNonDeclareeException Exception étant appelée lorsque l'utilisateur recherche une variable n'étant pas enregistrée dans la table des symboles.
     */
    public Symbole identifier(Entree e) throws EntreeNonDeclareeException {
        Symbole s = null;
        for (Map.Entry<Entree, Symbole> elem : this.tableDesSymboles.get(noBlocActuel).entrySet()) {
            if (elem.getKey().getIdf().equals(e.getIdf()) && elem.getKey().getType().equals(e.getType())) {
                s = new Symbole(elem.getValue().getType());
                s.setDeplacement(elem.getValue().getDeplacement());
            }
        }
        if (s == null) {
            throw new EntreeNonDeclareeException("Le symbole " + e.getIdf() + " n'existe pas dans la table des symboles !");
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
        //TODO : à modifier lorsque l'on comprendra comment passer d'allocation statique à dynamique.
        int taille = 0;
        for (HashMap<Entree, Symbole> hm : tableDesSymboles)
            taille += hm.size();
        taille *= -4;
        return taille;
    }

    /**
     * Fonction qui retourne le bloc dans lequel a TDS se trouve actuellement.
     *
     * @return le numéro du bloc
     */
    public int getNoBlocActuel() {
        return noBlocActuel;
    }

    /**
     * Procédure qui fait rentrer la TDS dans un nouveau bloc.
     */
    public void entreeBloc() {
        //On crée le nouveau bloc
        this.tableDesSymboles.add(new HashMap<>());
        //On se positionne dans le nouveau bloc.
        noBlocActuel = this.tableDesSymboles.size()-1;
    }

    /**
     * Procédure qui fait sortir la TDS d'un bloc.
     */
    public void sortieBloc() {
        //Quand on sort du bloc d'une fonction, on va dans le bloc principal (interdiction de déclarer une fonction dans une fonction).
        noBlocActuel = 0;
    }

    /**
     * Procédure qui fait rentrer la TDS dans le bloc suivant lors de la vérification.
     */
    public void entreeBlocVerif() {
        //On augmente le bloc précédent
        noBlocPrec++;
        //On se positionne dans le bloc suivant.
        noBlocActuel = noBlocPrec;
    }

    public void entreeBlocPrecVerif() {
        //On se positionne dans le bloc prec.
        noBlocActuel = noBlocPrec;
    }
}
