package zoot.arbre.expressions;
import java.lang.Math;

public class Addition extends Expression {
    private Expression eGauche;
    private Expression eDroite;

    public Addition(int n, Expression eGauche, Expression eDroite) {
        super(n);
        this.eDroite = eDroite;
        this.eGauche = eGauche;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS(String... registres) {
        System.out.println(this.getNbErchov());
        StringBuilder sb = new StringBuilder();
        sb.append(eGauche.toMIPS(registres));
        //Si on a encore au moins 1 registre temporaire disponible, on l'utilise
        if (registres.length != 1) {
            //On stocke le résultat dans un registre pour pouvoir réutiliser v0
            sb.append("\tmove ").append(registres[1]).append(", ").append(registres[0]).append("\n");

            sb.append(eDroite.toMIPS(supprRegistreInutile(1, registres)));
            //On ajoute les 2 entiers stockés dans les 2 registres, puis stocke le résultat de la somme de v0.
            sb.append("\tadd ").append(registres[0]).append(",").append(registres[0]).append(",").append(registres[1]).append("\n");
        } else { //Sinon, on utilise la pile
            //TODO à faire quand on à compris
        }
        return sb.toString();
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
        return eGauche.getNbErchov() == eDroite.getNbErchov() ?  eGauche.getNbErchov() + 1 : Math.max(eGauche.getNbErchov(), eDroite.getNbErchov());
    }

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
