package zoot.arbre;

import zoot.arbre.fonctions.GestionnaireFonctions;
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
        StringBuilder sb = new StringBuilder(programme.size() * 16);
        if (!GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si on est en train de traiter le bloc d'instruction du programme principal, on construit le code du programme principal.
            GestionnaireFonctions.getInstance().setFonctionsSontTraitees(true);
            sb.append("#IOPETI Hugo & YVOZ Ludovic\n\n");
            sb.append(".data\n");
            sb.append("vrai:\t.word 1\n");
            sb.append("faux:\t.word 0\n");
            sb.append("vraiAff:\t.asciiz \"vrai\"\n");
            sb.append("fauxAff:\t.asciiz \"faux\"\n");
            sb.append("sautLigne:\t.asciiz \"\\n\"\n");
            sb.append("erreurAff:\t.asciiz \"ERREUR EXECUTION\"\n\n");
            sb.append(".text\n");
            sb.append("main :\n");
            sb.append("\n# Initialiser $s1 avec la valeur vrai\n");
            sb.append("\tla $s1, faux\n");
            sb.append("\n# Initialiser $s7 avec $sp\n");
            sb.append("\tmove $s7, $sp\n");
            //On check si il existe une erreur de déclaration, si c'est le cas, on stop le programme mips à cet endroit là.
            if (StockageErreurs.getInstance().getNbErreurs() > 0 && StockageErreurs.getInstance().getErreurI(0).getType().equals("DECLARATION")) {
                sb.append("# Affichage de l'erreur lors de l'exécution du programme\n");
                sb.append("\tla $a0, erreurAff\n");
                sb.append("\tli $v0, 4\n");
                sb.append("\tsyscall\n");
                sb.append("\tb end\n\n");
            }
            sb.append("\n# Réserver la place pour ").append((TDS.getInstance().getTailleZoneVariables() * -1) / 4).append(" variables\n");
            sb.append("\tadd $sp, $sp, ").append(TDS.getInstance().getTailleZoneVariables()).append("\n\n");
            for (ArbreAbstrait a : programme) {
                //Si il y a une erreur et qu'elle se trouve à la ligne de cette instruction on remplace le code par un affichage d'erreur et on grâce à l'étiquette end on va à la fin du programme et on éxecute pas le code érroné.
                if (StockageErreurs.getInstance().getNbErreurs() != 0 && StockageErreurs.getInstance().getErreurI(0).getNumLigne() == a.getNoLigne()) {
                    sb.append("# Affichage de l'erreur lors de l'exécution du programme\n");
                    sb.append("\tla $a0, erreurAff\n");
                    sb.append("\tli $v0, 4\n");
                    sb.append("\tsyscall\n");
                    sb.append("\tb end\n\n");
                    sb.append("# L'instruction qui suit est celle qui à causé l'erreur\n");
                }
                sb.append(a.toMIPS()).append("\n");
            }
            sb.append("end :\n").append("\tli $v0, 10\n").append("\tsyscall");
            sb.append(GestionnaireFonctions.getInstance().toMipsFonctions());
        } else { //Sinon, on écrit le code des blocs d'instructions des fonctions
            for (ArbreAbstrait a : programme) { //On parcourt le bloc d'instruction de la fonction concernée.
                //Si il y a une erreur et qu'elle se trouve à la ligne de cette instruction on remplace le code par un affichage d'erreur et on grâce à l'étiquette end on va à la fin du programme et on éxecute pas le code érroné.
                if (StockageErreurs.getInstance().getNbErreurs() != 0 && StockageErreurs.getInstance().getErreurI(0).getNumLigne() == a.getNoLigne()) {
                    sb.append("# Affichage de l'erreur lors de l'exécution du programme\n");
                    sb.append("\tla $a0, erreurAff\n");
                    sb.append("\tli $v0, 4\n");
                    sb.append("\tsyscall\n");
                    sb.append("\tb end\n\n");
                    sb.append("# L'instruction qui suit est celle qui à causé l'erreur\n");
                }
                sb.append(a.toMIPS()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return programme.toString();
    }

}
