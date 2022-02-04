package zoot.arbre.expressions;

import zoot.exceptions.VariableNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Symbole;
import zoot.tableDesSymboles.TDS;

public class Idf extends Expression {

    private final String nom;
    private int depl;

    public Idf(String texte, int n) {
        super(n);
        this.nom = texte;
    }

    @Override
    public void verifier() {
        try {
            Symbole temp = TDS.getInstance().identifier(nom);
            this.depl = temp.getDeplacement();
        } catch (VariableNonDeclareeException e) {
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), this.getNoLigne()));
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String getType() throws VariableNonDeclareeException {
        return TDS.getInstance().identifier(nom).getType();
    }
}
