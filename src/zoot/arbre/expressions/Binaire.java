package zoot.arbre.expressions;

/**
 * Classe Binaire.
 */
public abstract class Binaire extends Expression {
    protected Expression eGauche;
    protected Expression eDroite;

    /**
     * Constructeur de la classe Binaire.
     *
     * @param n numéro de ligne
     */
    protected Binaire(int n, Expression eGauche, Expression eDroite) {
        super(n);
        this.eDroite = eDroite;
        this.eGauche = eGauche;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public boolean estUneVariable() {
        return false;
    }

    @Override
    public boolean estUnAppelDeFonction() {
        return false;
    }

    @Override
    public int getNbErchov() {
        return eGauche.getNbErchov() == eDroite.getNbErchov() ? eGauche.getNbErchov() + 1 : Math.max(eGauche.getNbErchov(), eDroite.getNbErchov());
    }

    /**
     * Foonction qui supprime un registre de la liste des registres libres lors de son utilisation.
     *
     * @param n         l'indice du registre utilisé/à supprimer
     * @param registres la liste de registres disponibles actuellement
     * @return la liste de registre disponible après l'appel de la fonction
     */
    public String[] supprRegistreInutile(int n, String... registres) {
        String[] temp = new String[registres.length - 1];
        int i = 0;
        for (int j = 0; j < registres.length; j++) {
            if (i == n)
                j++;
            temp[i] = registres[j];
            i++;
        }
        return temp;
    }
}
