package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Retourne extends Instruction {
    private final Expression exp;

    public Retourne(int n, Expression e) {
        super(n);
        this.exp = e;
    }

    @Override
    public void verifier() {
        //TODO : Vérifier si retourner est dans une fonction

        //Done : Vérifier si le type de retour est le même que celui de la fonction dans lequel le retour se trouve
        //TODO : Vérifier si on peux pas faire ça plus propre
        /*
        int stockageNoLigne = 0;
        for (Fonction f : GestionnaireFonctions.getInstance().getFonctions()) {
            if (f.getNoLigne() < this.noLigne)
                if (f.getNoLigne() >= stockageNoLigne)
                    stockageNoLigne = f.getNoLigne();
        }
        String idfFonction = GestionnaireFonctions.getInstance().getFonctionINumLigne(stockageNoLigne).getIdf();
        try {
            if (!TDS.getInstance().identifier(new Entree(idfFonction, "fonction")).getType().equals(exp.getType()))
                StockageErreurs.getInstance().ajouter(new Erreur("Le type de retour de la fonction ne correspond pas avec l'expression retournée !", noLigne));
        }
        catch (EntreeNonDeclareeException e){
            StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(),this.noLigne));
        }*/
    }

    @Override
    public String toMIPS() { //Appelé à la fin de la fonction.
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append("Retourne ").append(exp.toString()).append("\n");
        if (exp.estUneVariable()) {
            sb.append("\tlw $v0, ");
        } else { //Si c'est une constante
            if (exp.getType().equals("booleen")) {
                sb.append("\tla $v0, ");
            } else {
                sb.append("\tli $v0, ");
            }
        }
        sb.append(exp.toMIPS());
        return sb.toString();
    }
}
