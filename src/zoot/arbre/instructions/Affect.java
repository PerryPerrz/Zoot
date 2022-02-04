package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.VariableNonDeclareeException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public class Affect extends Instruction {

    protected Idf idf;
    protected Expression exp;

    public Affect(Idf i, Expression e, int n) {
        super(n);
        idf = i;
        exp = e;
    }

    @Override
    public void verifier() {
        try {
            //On vérifie que la variable et l'expression sont du même type.
            if (idf.getType().equals(exp.getType())) {
                idf.verifier();
                exp.verifier();
            } else {
                //Sinon, on stocke l'erreur et passe à la suite
                StockageErreurs.getInstance().ajouter(new Erreur("Attention le type de la variable et le type de l'expression ne correspondent pas !", this.getNoLigne()));
            }
        } catch (VariableNonDeclareeException e) {
            //Si la variable ou l'exception contiennent une variable non déclarée, on stocke l'erreur et passe à la suite
            StockageErreurs.getInstance().ajouter(new Erreur("Attention, une des variables de l'expression n'est pas déclarée !", this.getNoLigne()));
        }
    }

    @Override
    public String toMIPS() {
        return "# " + idf.toString() + " = " + exp.toString() +
                "\tli $v0, " + exp.toMIPS() + "\n" +
                "\tsw $v0, " + idf.toMIPS() + "\n";
    }

}
