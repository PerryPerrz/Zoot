package zoot.tableDesSymboles;

public class Entree {
    private String idf;
    private String type; //Vaut soit "variable" soit "fonction"

    public Entree (String idf, String type){
        this.idf = idf;
        this.type = type;
    }

    public String getIdf() {
        return idf;
    }

    public String getType() {
        return type;
    }
}
