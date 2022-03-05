package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Retourne extends Instruction {
    private final Expression exp;

    public Retourne(int n, Expression e) {
        super(n);
        this.exp = e;
    }

    @Override
    public void verifier() {
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
        sb.append(exp.toMIPS()).append("\n");
        return sb.toString();
    }
}
