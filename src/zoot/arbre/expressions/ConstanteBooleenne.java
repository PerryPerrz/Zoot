package zoot.arbre.expressions;

/**
 * Classe ConstanteBooleenne qui représente les constantes booléennes (vrai/faux)
 */
public class ConstanteBooleenne extends Constante {

    public ConstanteBooleenne(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        String res;
        if (this.cste.equals("vrai")) { //La valeur est vrai
            res = "1";
        } else { //La valeur est faux
            res = "0";
        }
        return res;
    }

    @Override
    public String getType() {
        return "booleen";
    }
}
