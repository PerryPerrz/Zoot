package zoot.tableDesSymboles;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDeclareeException;

/**
 * Singleton TDS représentant la table des symboles de zoot.
 */
public class TDS {
    private ArbreTDS arbre;
    private int numBlocActu;
    private int numBlocActuMax; //Numéro qui stock le numéro du bloc possédant l'id le plus élevé. Permet de palier au problème des id : je créer un fils, je sort, je créer un autre fils

    /**
     * Constructeur privé du singleton TDS.
     */
    private TDS() {
        this.numBlocActu = 1;
        this.numBlocActuMax = 1;
        this.arbre = new ArbreTDS(this.numBlocActu, null);
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
     * Procédure qui matérialise l'action de rentrer dans un bloc.
     */
    public void entreeBloc() {
        this.numBlocActuMax++;
        this.arbre = new ArbreTDS(this.numBlocActuMax, this.arbre);
        //Mise à jour du numéro du bloc actuel.
        this.numBlocActu = this.arbre.getId();
    }

    /**
     * Procédure qui matérialise l'action de sortir d'un bloc.
     */
    public void sortieBloc() {
        this.arbre = this.arbre.getPere();
        this.numBlocActu = this.arbre.getId();
    }

    /**
     * Procédure qui matérialise l'action de rentrer dans un bloc. (sans créer de nouveau fils)
     */
    public void entreeBlocASem() {
        this.numBlocActuMax++;
        for (ArbreTDS a : this.arbre.getListeDeFils())
            if (a.getId() == this.numBlocActuMax)
                this.arbre = a;
        //Mise à jour du numéro du bloc actuel.
        this.numBlocActu = this.arbre.getId();
    }

    /**
     * Procédure qui matérialise l'action de sortir dans un bloc. (sans créer de nouveau fils)
     */
    public void sortieBlocASem() {
        this.arbre = this.arbre.getPere();
        this.numBlocActu = this.arbre.getId();
    }

    /**
     * Procédure qui ajoute une variable ou une fonction au bloc actuel.
     *
     * @param e l'entrée correspondant à la variable ou à la fonction que l'on veut ajouter
     * @param s le symbole correspondant à la variable ou à la fonction que l'on veut ajouter
     * @throws DoubleDeclarationException Exception étant appelée lorsque l'utilisateur veut déclarer une variable déjà inscrite dans la table des symboles.
     */
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationException {
        this.arbre.ajouter(e, s);
    }

    /**
     * Fonction qui permet d'identifier une entrée dans le bloc actuel.
     *
     * @param e entrée que l'on veut identifier
     * @return le Symbole correspondant à l'entrée
     * @throws VariableNonDeclareeException Exception étant appelée lorsque l'utilisateur recherche une variable n'étant pas enregistrée dans la table des symboles.
     */
    public Symbole identifier(Entree e) throws VariableNonDeclareeException {
        return this.arbre.identifier(e);
    }
}
