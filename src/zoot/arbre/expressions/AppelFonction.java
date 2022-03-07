package zoot.arbre.expressions;

import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.exceptions.EntreeNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.TDS;

public class AppelFonction extends Expression {
    private final String idf;

    public AppelFonction(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        if (GestionnaireFonctions.getInstance().isFonctionsSontTraitees()) { //Si on est dans une fonction
            //On regarde la fonction la plus proche de l'expression appel fonction pour regarder si cette même fonction ne s'appelle pas récursivement.
            int stockageNoLigne = 0;
            for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
                if (f.getNoLigne() < this.noLigne)
                    if (f.getNoLigne() >= stockageNoLigne)
                        stockageNoLigne = f.getNoLigne();
            }
            //Une fois que l'on a identifié la fonction dans laquelle on se trouve, on la stocke
            String idfFonction = GestionnaireFonctions.getInstance().getFonctionINumLigne(stockageNoLigne).getIdf();
            //On vérifie à présent que la fonction soit du même type de retour que l'espression que l'on essaie de retourner

            if (idfFonction.equals(this.idf)) { //Si c'est la même, cela signifie que c'est un appel récursif
                StockageErreurs.getInstance().ajouter(new Erreur("Appel récursif d'une fonction non autorisée dans zoot !", noLigne));
            }
        }
    }

    @Override
    //On jump vers l'étiquette.
    public String toMIPS() {
        return "jal " + this.idf;
    }

    @Override
    public String getType() {
        String type = null;
        try {
            type = TDS.getInstance().identifier(new Entree(this.idf, "fonction")).getType();
        } catch (EntreeNonDeclareeException e) {
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.noLigne));
        }
        return type;
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
