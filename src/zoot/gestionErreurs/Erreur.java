package zoot.gestionErreurs;

public class Erreur {
    private String message;
    private int numLigne;
    private int numCarac;

    public Erreur(String message, int numLigne, int numCarac) {
        this.message = message;
        this.numLigne = numLigne;
        this.numCarac = numCarac;
    }

    public Erreur(String message, int numLigne) {
        this.message = message;
        this.numLigne = numLigne;
        this.numCarac = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public int getNumCarac() {
        return numCarac;
    }

    public void setNumCarac(int numCarac) {
        this.numCarac = numCarac;
    }
}
