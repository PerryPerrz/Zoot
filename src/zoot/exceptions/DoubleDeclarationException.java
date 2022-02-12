package zoot.exceptions;

/**
 * Classe DoubleDeclarationException représentant l'exception se déclenchant lorsque le compilateur détecte une double déclaration d'une variable.
 */
public class DoubleDeclarationException extends AnalyseLexicaleSemantique {
    public DoubleDeclarationException(String m) {
        super(m);
    }
}
