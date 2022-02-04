package zoot.exceptions;

/**
 * Classe VariableNonDeclareeException représentant l'exception se déclenchant lorsque le compilateur détecte une variable utilisée sans avoir été déclarée.
 */
public class VariableNonDeclareeException extends AnalyseSyntaxiqueException {
    public VariableNonDeclareeException(String m) {
        super(m);
    }
}
