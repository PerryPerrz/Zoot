package zoot.arbre.expressions;

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
        //Il n'y a rien à vérifier dans cette classe
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
