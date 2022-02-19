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
        return cste;
    }

    @Override
    public String getType() {
        return "booleen";
    }
}
