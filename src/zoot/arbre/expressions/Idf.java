package zoot.arbre.expressions;

import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.Symbole;
import zoot.tableDesSymboles.TDS;

/**
 * Classe Idf.
 */
public class Idf extends Expression {

    private final String nom;
    private int depl;
    private String type;

    public Idf(String texte, int n) {
        super(n);
        this.nom = texte;
    }

    @Override
    public void verifier() {
        try {
            //Si la variable existe déjà, on stocke sa position dans la pile (déplacement).
            Symbole temp = TDS.getInstance().identifier(new Entree(nom, "variable"));
            this.depl = temp.getDeplacement();
            this.type = temp.getType();
        } catch (EntreeNonDeclareeException e) {
            //Sinon, on ajoute une erreur, on donne un type erreur et on passe à la suite.
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.getNoLigne()));
            this.type = "erreur";
        }
    }

    @Override
    public String toMIPS() {
        return "\tlw $v0, " + this.depl + "($s7)\n";
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * Fonction qui retourne le déplacement d'un idf
     *
     * @return le déplacement d'un idf
     */
    public int getDepl() {
        return depl;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean estUneVariable() {
        return true;
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return false;
    }
}
