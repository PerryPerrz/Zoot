package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.tableDesSymboles.TDS;

/**
 * Classe Ecrire.
 */
public class Ecrire extends Instruction {

    protected Expression exp;

    /**
     * Constructeur de la classe Ecrire.
     *
     * @param e l'expression
     * @param n le numéro de ligne
     */
    public Ecrire(Expression e, int n) {
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();

        //Ecriture d'un commentaire adapté
        sb.append("# Ecrire ");
        if (!exp.estUnAppelDeFonction()) {
            sb.append(exp.toString()).append("\n");
        } else {
            sb.append(this.exp.getSignatureFonction()).append(" (").append(this.exp.getTypeParam()).append(")\n");
        }

        //Affichage de la valeur
        //On met le résultat de l'expression dans v0 pour l'afficher ensuite
        sb.append(exp.toMIPS());
        //Si c'est un booleen on l'affiche tel quel
        if (exp.getType().equals("booleen")) {
            sb.append("\tbeq $s1, $v0, Sinon").append(noLigne).append("\n"); //TODO s1 ???????
            sb.append("\tla $a0, vraiAff\n");
            sb.append("\tli $v0, 4\n");
            sb.append("\tsyscall\n");
            sb.append("\tb FinSi").append(noLigne).append("\n");
            sb.append("Sinon").append(noLigne).append(":").append("\n");
            sb.append("\tla $a0, fauxAff\n");
            sb.append("\tli $v0, 4\n");
            sb.append("\tsyscall\n");
            sb.append("FinSi").append(noLigne).append(":\n");
        } else {
            sb.append("\tmove $a0, $v0\n"); //On met le résultat de l'expression
            sb.append("\tli $v0, 1\n");
            sb.append("\tsyscall\n");
        }
        //Affichage d'un saut de ligne
        sb.append("\tla $a0, sautLigne\n");
        sb.append("\tli $v0, 4\n");
        sb.append("\tsyscall\n");

        return sb.toString();
    }
}
