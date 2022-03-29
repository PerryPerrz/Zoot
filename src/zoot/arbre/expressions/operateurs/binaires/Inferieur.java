package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public class Inferieur extends Expression {
    private final Expression eGauche;
    private final Expression eDroite;

    public Inferieur(int n, Expression eGauche, Expression eDroite) {
        super(n);
        this.eGauche = eGauche;
        this.eDroite = eDroite;
    }

    @Override
    public void verifier() {
        eGauche.verifier();
        eDroite.verifier();
        if (!eDroite.getType().equals("entier") || !eGauche.getType().equals("entier"))
            StockageErreurs.getInstance().ajouter(new Erreur("Une des expressions qui entoure un '<' n'est pas entière !", noLigne));
    }

    @Override
    public String toMIPS(String... registres) {
        StringBuilder sb = new StringBuilder();
        //TODO : Comparer
        return sb.toString();
    }

    @Override
    public String getType() {
        return "booleen";
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

    @Override
    public String toString() {
        return eGauche.toString() + "<" + eDroite.toString();
    }
}
