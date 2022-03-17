package zoot.arbre.expressions;

import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.TDS;

import java.util.ArrayList;

/**
 * Classe AppelFonction.
 */
public class AppelFonction extends Expression {
    private final String idf;
    private String type;
    private final ArrayList<Expression> params;

    /**
     * Constructeur de la classe AppelFonction.
     *
     * @param n   numéro de la ligne
     * @param idf identifiant de la fonction appelée
     */
    public AppelFonction(int n, String idf) {
        super(n);
        this.idf = idf;
        this.params = new ArrayList<>();
    }

    /**
     * Constructeur de la classe AppelFonction.
     *
     * @param n   numéro de la ligne
     * @param idf identifiant de la fonction appelée
     * @param params paramètres de la fonction appelée
     */
    public AppelFonction(int n, String idf, ArrayList<Expression> params) {
        super(n);
        this.idf = idf;
        this.params = params;
    }

    @Override
    public void verifier() {
        //On vérifie les paramètres
        if (params != null) {
            for (Expression e : params) {
                e.verifier();
            }
        }

        if (GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si on est dans une fonction
            //On regarde la fonction la plus proche de l'expression appel fonction pour regarder si cette même fonction ne s'appelle pas récursivement.
            //On vérifie également qu'il existe bien une fonction que l'on appelle (vérficiation des paramètres.
            int stockageNoLigne = 0;
            Fonction fonctionProche = null;
            for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne)
                    if (f.getNoLigne() >= stockageNoLigne) {
                        stockageNoLigne = f.getNoLigne();
                        fonctionProche = f;
                    }
            }

            //On vérifie que cet appel ne soit pas un appel récursif
            if (fonctionProche != null)
                if (fonctionProche.getIdf().equals(this.idf) && fonctionProche.getNbParams() == params.size()) //Si c'est la même, cela signifie que c'est un appel récursif
                    StockageErreurs.getInstance().ajouter(new Erreur("Appel récursif d'une fonction non autorisée dans zoot !", noLigne));
        }

        //On vérifie si l'appel correspond bien à une fonction au niveau des paramètres
        Fonction fonctionAppelee = null;
        for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
            if (f.getIdf().equals(this.idf) && f.getNbParams() == this.params.size()) {
                boolean estLaBonne = true;
                for (int i = 0; i < f.getNbParams(); i++) {
                    if (!f.getTypeParam()[i].equals(this.params.get(i).getType()))
                        estLaBonne = false;
                }
                if (estLaBonne)
                    fonctionAppelee = f;
            }
        }
        if (fonctionAppelee == null)
            StockageErreurs.getInstance().ajouter(new Erreur("Aucun prototype de fonction ne correspond à cet appel !", noLigne));



        //On récupère le type de retour de la fonction
        try {
            this.type = TDS.getInstance().identifier(new Entree(this.idf, "fonction")).getType();
            TDS.getInstance().identifier(new Entree(this.idf, "fonction"));
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
