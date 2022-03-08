package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.TDS;

/**
 * CLasse Retourne.
 */
public class Retourne extends Instruction {
    private final Expression exp;

    /**
     * Constructeur de la classe Retourne.
     *
     * @param n numéro de ligne
     * @param e l'expression
     */
    public Retourne(int n, Expression e) {
        super(n);
        this.exp = e;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
        if (!GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si retourner n'est pas dans une fonction
            StockageErreurs.getInstance().ajouter(new Erreur("Instruction retourner en dehors d'une fonction !", noLigne));
        } else {
            //On regarde la fonction la plus proche de l'instruction retourner pour regarder son type mainentant que l'on sait que l'on est dans une fonction
            int stockageNoLigne = 0;
            String idfFonction = "";
            for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne)
                    if (f.getNoLigne() >= stockageNoLigne) {
                        stockageNoLigne = f.getNoLigne();
                        idfFonction = f.getIdf();
                    }
            }
            //On vérifie à présent que la fonction soit du même type de retour que l'espression que l'on essaie de retourner
            try {
                if (!TDS.getInstance().identifier(new Entree(idfFonction, "fonction")).getType().equals(exp.getType()))
                    StockageErreurs.getInstance().ajouter(new Erreur("Le type de retour de la fonction ne correspond pas avec l'expression retournée !", noLigne));
            } catch (EntreeNonDeclareeException e) {
                StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.noLigne));
            }
        }
    }

    @Override
    public String toMIPS() { //Appelé à la fin de la fonction.
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append("Retourne ");
        if (!exp.estUnAppelDeFonction()) {
            sb.append(exp).append("\n");
            if (exp.estUneVariable()) {
                sb.append("\tlw $v0, ");
            } else { //Si c'est une constante
                if (exp.getType().equals("booleen")) {
                    sb.append("\tla $v0, ");
                } else {
                    sb.append("\tli $v0, ");
                }
            }
            sb.append(exp.toMIPS());
        } else {
            sb.append(this.exp.getSignatureFonction()).append("()\n");
            sb.append("\t").append(exp.toMIPS()).append("\n");
            sb.append("# Rien à faire vu que le résultat de l'ancienne fonction est déjà dans $v0 !\n");
        }
        return sb.toString();
    }
}
