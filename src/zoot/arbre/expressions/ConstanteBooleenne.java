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
        return "\tla $v0, " + this.cste + "\n";
    }

    @Override
    public String getType() {
        return "booleen";
    }
}
