package zoot.arbre.expressions.operateurs.binaires;

import zoot.arbre.expressions.Expression;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;

public class Inferieur extends Expression {
    private final Expression eGauche;
    private final Expression eDroite;
    private final int numCarac;

    public Inferieur(int n, Expression eGauche, Expression eDroite, int numCarac) {
        super(n);
        this.eGauche = eGauche;
        this.eDroite = eDroite;
        this.numCarac = numCarac;
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
        sb.append(eGauche.toMIPS(registres));
        //Si on a encore au moins 1 registre temporaire disponible, on l'utilise
        if (registres.length != 1) {
            //On stocke le résultat dans un registre pour pouvoir réutiliser v0
            sb.append("\tmove ").append(registres[1]).append(", ").append(registres[0]).append("\n");

            sb.append(eDroite.toMIPS(supprRegistreInutile(1, registres)));
            sb.append("\tsub $t8, ").append(registres[1]).append(", $v0\n");
            sb.append("\tbltz $t8, Sinon").append(noLigne).append("Car").append(numCarac).append("\n");
            sb.append("\tla $v0, faux\n");
            sb.append("\tb FinSi").append(noLigne).append("Car").append(numCarac).append("\n");
            sb.append("Sinon").append(noLigne).append("Car").append(numCarac).append(":").append("\n");
            sb.append("\tla $v0, vrai\n");
            sb.append("FinSi").append(noLigne).append("Car").append(numCarac).append(":\n");
        } else { //Sinon, on utilise la pile
            //TODO à faire quand on à compris
            System.out.println("Pas encore implémenté !");
        }
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
