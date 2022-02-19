package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp;

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# Ecrire ").append(exp.toString()).append("\n");
        if (exp.estUneVariable()) {
            stringBuilder.append("\tlw $a0, ").append(exp.toMIPS()).append("\n");
            stringBuilder.append("\tli $v0, 1\n");
        } else {
            if (exp.getType().equals("booleen")) {
                stringBuilder.append("\tla $a0, ").append(exp.toMIPS()).append("Aff\n");
                stringBuilder.append("\tli $v0, 4\n");
            } else {
                stringBuilder.append("\tli $a0, ").append(exp.toMIPS()).append("\n");
                stringBuilder.append("\tli $v0, 1\n");
            }
        }
        stringBuilder.append("\tsyscall\n");
        //Affichage d'un saut de ligne
        stringBuilder.append("\tla $a0, sautLigne\n");
        stringBuilder.append("\tli $v0, 4\n");
        stringBuilder.append("\tsyscall\n");

        return stringBuilder.toString();
    }

}
