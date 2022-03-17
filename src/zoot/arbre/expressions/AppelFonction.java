package zoot.arbre.expressions;

import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.TDS;

/**
 * Classe AppelFonction.
 */
public class AppelFonction extends Expression {
    private final String idf;
    private String type;

    /**
     * Constructeur de la classe AppelFonction
     *
     * @param n   numéro de la ligne
     * @param idf identifiant de la fonction appelée
     */
    public AppelFonction(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        if (GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si on est dans une fonction
            //On regarde la fonction la plus proche de l'expression appel fonction pour regarder si cette même fonction ne s'appelle pas récursivement.
            int stockageNoLigne = 0;
            String idfFonction = "";
            for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne)
                    if (f.getNoLigne() >= stockageNoLigne) {
                        stockageNoLigne = f.getNoLigne();
                        idfFonction = f.getIdf();
                    }
            }

            if (idfFonction.equals(this.idf)) { //Si c'est la même, cela signifie que c'est un appel récursif
                StockageErreurs.getInstance().ajouter(new Erreur("Appel récursif d'une fonction non autorisée dans zoot !", noLigne));
            }
        }
        //On récupère le type de retour de la fonction
        try {
            this.type = TDS.getInstance().identifier(new Entree(this.idf, "fonction")).getType();
        } catch (EntreeNonDeclareeException e) {
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), noLigne));
            this.type = "erreur";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n# Sauvegarde des registres avant l'appel de fonction.\n");
        sb.append("\tsw $ra,0($sp)\n");
        sb.append("\tsw $s1,-4($sp)\n");
        sb.append("\taddi $sp,$sp,-8\n");
        sb.append("\t# Appel de la fonction.\n");
        sb.append("\tjal ").append(this.idf).append("\n");
        sb.append("\t# Restauration des registres après l'appel de fonction.\n");
        sb.append("\tlw $s1,4($sp)\n");
        sb.append("\tlw $ra,8($sp)\n");
        sb.append("\taddi $sp,$sp,8\n");
        return sb.toString();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean estUneVariable() {
        return false;
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return true;
    }

    @Override
    public String getSignatureFonction() {
        return this.idf;
    }
}
