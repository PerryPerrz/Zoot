package zoot.gestionErreurs;

public class Erreur {
    private String message;
    private int numLigne;

    public Erreur(String message, int numLigne) {
        this.message = message;
        this.numLigne = numLigne;
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
}
