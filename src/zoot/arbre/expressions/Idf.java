package zoot.arbre.expressions;

import zoot.arbre.fonctions.GestionnaireFonctions;
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
            //Si la variable existe bien, on stocke sa position dans la pile (déplacement).
            Symbole temp = TDS.getInstance().identifier(new Entree(nom, "variable"));
            this.depl = temp.getDeplacement();
            this.type = temp.getType();
        } catch (EntreeNonDeclareeException e) {
            //Sinon si on est dans une fonction, on cherche si la variable existe dans le bloc supérieur (le main)
            if (GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) {
                try {
                    //On reviens dans le bloc du main
                    TDS.getInstance().sortieBloc();

                    //Si la variable existe bien, on stocke sa position dans la pile (déplacement).
                    Symbole temp2 = TDS.getInstance().identifier(new Entree(nom, "variable"));
                    this.depl = temp2.getDeplacement();
                    this.type = temp2.getType();

                    //On reviens dans le bloc de la fonction
                    TDS.getInstance().entreeBlocVerifIDF();
                } catch (EntreeNonDeclareeException e2) {
                    //Sinon, on ajoute une erreur, on donne un type erreur et on passe à la suite.
                    StockageErreurs.getInstance().ajouter(new Erreur(e2.getMessage(), this.getNoLigne()));
                    this.type = "erreur";
                }
            } else {
                //Sinon, on ajoute une erreur, on donne un type erreur et on passe à la suite.
                StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.getNoLigne()));
                this.type = "erreur";
            }
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
