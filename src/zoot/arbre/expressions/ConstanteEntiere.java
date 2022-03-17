package zoot.arbre.expressions;

/**
 * Classe ConstanteEntière qui représente les constantes entières (nombres)
 */
public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        return "\t li $v0, " + this.cste + "\n";
    }

    @Override
    public String getType() {
        return "entier";
    }
}
