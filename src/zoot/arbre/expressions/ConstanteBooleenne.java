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
        throw new UnsupportedOperationException("toMips des expressions booléennes pas encore fait ");
    }

    @Override
    public String getType() {
        return "Booleen";
    }
}
