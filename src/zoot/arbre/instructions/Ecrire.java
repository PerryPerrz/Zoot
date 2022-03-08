package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

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
        sb.append("# Ecrire ");
        if (!exp.estUnAppelDeFonction()) {
            sb.append(exp.toString()).append("\n");
            if (exp.estUneVariable()) {
                if (exp.getType().equals("booleen")) {
                    sb.append("\tlw $t0,").append(exp.toMIPS()).append("\n");
                    sb.append("\tbeq $s1, $t0, Sinon").append(noLigne).append("\n");
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
                    sb.append("\tlw $a0, ").append(exp.toMIPS()).append("\n");
                    sb.append("\tli $v0, 1\n");
                    sb.append("\tsyscall\n");
                }
            } else {
                if (exp.getType().equals("booleen")) {
                    sb.append("\tla $a0, ").append(exp.toMIPS()).append("Aff\n");
                    sb.append("\tli $v0, 4\n");
                    sb.append("\tsyscall\n");
                } else {
                    sb.append("\tli $a0, ").append(exp.toMIPS()).append("\n");
                    sb.append("\tli $v0, 1\n");
                    sb.append("\tsyscall\n");
                }
            }
        } else {
            sb.append(this.exp.getSignatureFonction()).append("()\n");
            sb.append("\t").append(exp.toMIPS()).append("\n"); //On va faire la fonction (jal)
            if (exp.getType().equals("booleen")) {
                sb.append("\tmove $t0, $v0\n");
                sb.append("\tbeq $s1, $t0, Sinon").append(noLigne).append("\n");
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
                sb.append("\tmove $a0, $v0\n"); //On met le résultat de la fonction dans le registre $a0 (celui que l'on affiche)
                sb.append("\tli $v0, 1\n");
                sb.append("\tsyscall\n");
            }
        }
        //Affichage d'un saut de ligne
        sb.append("\tla $a0, sautLigne\n");
        sb.append("\tli $v0, 4\n");
        sb.append("\tsyscall\n");

        return sb.toString();
    }

}
