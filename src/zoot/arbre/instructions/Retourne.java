package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.TDS;

public class Retourne extends Instruction {
    private final Expression exp;

    public Retourne(int n, Expression e) {
        super(n);
        this.exp = e;
    }

    @Override
    public void verifier() {
        if (!GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si retourner n'est pas dans une fonction
            StockageErreurs.getInstance().ajouter(new Erreur("Instruction retourner en dehors d'une fonction !", noLigne));
        } else {
            //On regarde la fonction la plus proche de l'instruction retourner pour regarder son type mainentant que l'on sait que l'on est dans une fonction
            int stockageNoLigne = 0;
            for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne)
                    if (f.getNoLigne() >= stockageNoLigne)
                        stockageNoLigne = f.getNoLigne();
            }
            //Une fois que l'on a identifié la fonction dans laquelle on se trouve, on la stocke
            String idfFonction = GestionnaireFonctions.getInstance().getFonctionINumLigne(stockageNoLigne).getIdf();
            //On vérifie à présent que la fonction soit du même type de retour que l'espression que l'on essaie de retourner
            try {
                if (!TDS.getInstance().identifier(new Entree(idfFonction, "fonction")).getType().equals(exp.getType()))
                    StockageErreurs.getInstance().ajouter(new Erreur("Le type de retour de la fonction ne correspond pas avec l'expression retournée !", noLigne));
            }
            catch (EntreeNonDeclareeException e){
                StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(),this.noLigne));
            }
        }
    }

    @Override
    public String toMIPS() { //Appelé à la fin de la fonction.
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append("Retourne ").append(exp.toString()).append("\n");
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
        return sb.toString();
    }
}
