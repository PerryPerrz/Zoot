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
        stringBuilder.append("sautLigne:\t.asciiz \"\\n\"\n");
        stringBuilder.append("erreurAff:\t.asciiz \"ERREUR EXECUTION\"\n\n");
        stringBuilder.append(".text\n");
        stringBuilder.append("main :\n");
        stringBuilder.append("\n# Initialiser $s1 avec la valeur vrai\n");
        stringBuilder.append("\tla $s1, faux\n");
        stringBuilder.append("\n# Initialiser $s7 avec $sp\n");
        stringBuilder.append("\tmove $s7, $sp\n");
        stringBuilder.append("\n# Réserver la place pour ").append((TDS.getInstance().getTailleZoneVariables() * -1) / 4).append(" variables\n");
        stringBuilder.append("\tadd $sp, $sp, ").append(TDS.getInstance().getTailleZoneVariables()).append("\n\n");
        for (ArbreAbstrait a : programme) {
            //Si il y a une erreur et qu'elle se trouve à la ligne de cette instruction on remplace le code par un affichage d'erreur et on grâce à l'étiquette end on va à la fin du programme et on éxecute pas le code érroné.
            if (StockageErreurs.getInstance().getNbErreurs() != 0 && StockageErreurs.getInstance().getErreurI(0).getNumLigne() == a.getNoLigne()) {
                stringBuilder.append("# Affichage de l'erreur lors de l'exécution du programme\n");
                stringBuilder.append("\tla $a0, erreurAff\n");
                stringBuilder.append("\tli $v0, 4\n");
                stringBuilder.append("\tsyscall\n");
                stringBuilder.append("\tb end\n\n");
                stringBuilder.append("# L'instruction qui suit est celle qui à causé l'erreur\n");
            }
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
