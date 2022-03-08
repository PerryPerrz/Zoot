package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

/**
 * Classe Affect.
 */
public class Affect extends Instruction {

    protected Idf idf;
    protected Expression exp;

    /**
     * Constructeur de la classe Affect.
     *
     * @param i l'identifiant
     * @param e l'expression
     * @param n le numéro de ligne
     */
    public Affect(Idf i, Expression e, int n) {
        super(n);
        idf = i;
        exp = e;
    }

    @Override
    public void verifier() {
        try {
            //On vérifie que la variable et l'expression sont du même type.
            if (idf.getType().equals(exp.getType())) {
                idf.verifier();
                exp.verifier();
            } else {
                //Sinon, on stocke l'erreur et passe à la suite
                StockageErreurs.getInstance().ajouter(new Erreur("Attention le type de la variable et le type de l'expression ne correspondent pas !", this.getNoLigne()));
            }
        } catch (EntreeNonDeclareeException e) {
            //Si la variable ou l'exception contiennent une variable non déclarée, on stocke l'erreur et passe à la suite
            StockageErreurs.getInstance().ajouter(new Erreur("Attention, une des variables de l'expression n'est pas déclarée !", this.getNoLigne()));
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(idf.toString()).append(" = ");
        if (!exp.estUnAppelDeFonction()) {
            sb.append(exp.toString()).append("\n");
            if (exp.estUneVariable()) {
                sb.append("\tlw $v0, ");
            } else { //Si c'est une constante
                if (exp.getType().equals("booleen")) {
                    sb.append("\tla $v0, ");
                } else {
                    sb.append("\tli $v0, ");
                }
            }
            sb.append(exp.toMIPS()).append("\n");
            sb.append("\tsw $v0, ").append(idf.toMIPS()).append("\n");

        } else {
            sb.append(this.exp.getSignatureFonction()).append("()\n");
            sb.append("\t").append(exp.toMIPS()).append("\n");
            sb.append("\t").append("sw $v0, ").append(idf.toMIPS()).append("\n");
        }
        return sb.toString();
    }

}
