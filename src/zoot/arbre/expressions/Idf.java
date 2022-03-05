package zoot.arbre.expressions;

import zoot.exceptions.VariableNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.Symbole;
import zoot.tableDesSymboles.TDS;

public class Idf extends Expression {

    private final String nom;
    private int depl;

    public Idf(String texte, int n) {
        super(n);
        this.nom = texte;
    }

    @Override
    public void verifier() {
        try {
            //Si la variable existe déjà, on stocke sa position dans la pile (déplacement).
            Symbole temp = TDS.getInstance().identifier(new Entree(nom,"variable"));
            this.depl = temp.getDeplacement();
        } catch (VariableNonDeclareeException e) {
            //Sinon, on ajoute une erreur et on passe à la suite.
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.getNoLigne()));
        }
    }

    @Override
    public String toMIPS() {
        return this.depl + "($s7)";
    }

    /**
     * Fonction qui retourne le type de la variable
     *
     * @return une chaîne de caractère correspondant au type de la variable ("entier" ou "booleen").
     * @throws VariableNonDeclareeException exception se déclenchant si la variable n'est pas déclarée.
     */
    @Override
    public String getType() throws VariableNonDeclareeException {
        return TDS.getInstance().identifier(new Entree(nom,"variable")).getType();
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean estUneVariable() {
        return true;
    }
}
