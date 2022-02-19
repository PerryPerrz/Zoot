package zoot.arbre;

import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.TDS;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<ArbreAbstrait> programme;

    public BlocDInstructions(int n) {
        super(n);
        programme = new ArrayList<>();
    }

    public void ajouter(ArbreAbstrait a) {
        programme.add(a);
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait a : programme) {
            a.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder(programme.size() * 16);
        stringBuilder.append("#IOPETI Hugo & YVOZ Ludovic\n\n");
        stringBuilder.append(".data\n");
        stringBuilder.append("vrai:\t.word 1\n");
        stringBuilder.append("faux:\t.word 0\n");
        stringBuilder.append("vraiAff:\t.asciiz \"vrai\"\n");
        stringBuilder.append("fauxAff:\t.asciiz \"faux\"\n");
        stringBuilder.append("sautLigne:\t.asciiz \"\\n\"\n\n");
        stringBuilder.append(".text\n");
        stringBuilder.append("main :\n");
        stringBuilder.append("# Initialiser $s7 avec $sp\n");
        stringBuilder.append("\tmove $s7, $sp\n");
        stringBuilder.append("# Réserver la place pour ").append((TDS.getInstance().getTailleZoneVariables() * -1) / 4).append(" variables\n");
        stringBuilder.append("\tadd $sp, $sp, ").append(TDS.getInstance().getTailleZoneVariables()).append("\n\n");
        for (ArbreAbstrait a : programme) {
            stringBuilder.append(a.toMIPS()).append("\n");
        }
        stringBuilder.append("end :\n").append("\tli $v0, 10\n").append("\tsyscall");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
