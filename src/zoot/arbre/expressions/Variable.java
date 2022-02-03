package zoot.arbre.expressions;

public abstract class Variable extends Expression {

    protected String var ;

    protected Variable(String texte, int n) {
        super(n) ;
        var = texte ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toString() {
        return var ;
    }

}