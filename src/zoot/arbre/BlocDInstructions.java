package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> programme;

    public BlocDInstructions(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public void ajouter(Instruction i) {
        programme.add(i);
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verifier non définie ");
    }

    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder(programme.size() * 16);
        stringBuilder.append("#IOPETI Hugo & YVOZ Ludovic\n");
        stringBuilder.append(".text\n");
        stringBuilder.append("main :\n");
        for (Instruction i : programme) {
            stringBuilder.append(i.toMIPS());
        }
        stringBuilder.append("end :\n").append("\tli $v0, 10\n").append("\tsyscall");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
