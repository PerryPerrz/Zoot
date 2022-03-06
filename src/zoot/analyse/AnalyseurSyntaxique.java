//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Mar 06 13:14:49 CET 2022
//----------------------------------------------------

package zoot.analyse;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.*;
import zoot.arbre.fonctions.Fonction;
import zoot.arbre.fonctions.GestionnaireFonctions;
import zoot.arbre.instructions.Affect;
import zoot.arbre.instructions.Ecrire;
import zoot.arbre.instructions.Instruction;
import zoot.arbre.instructions.Retourne;
import zoot.exceptions.AnalyseSyntaxiqueException;
import zoot.exceptions.DoubleDeclarationException;
import zoot.gestionErreurs.Erreur;
import zoot.gestionErreurs.StockageErreurs;
import zoot.tableDesSymboles.Entree;
import zoot.tableDesSymboles.Symbole;
import zoot.tableDesSymboles.TDS;

import java.util.HashMap;

/**
 * CUP v0.11a beta 20060608 generated parser.
 *
 * @version Sun Mar 06 13:14:49 CET 2022
 */
public class AnalyseurSyntaxique extends java_cup.runtime.lr_parser {

    /**
     * Default constructor.
     */
    public AnalyseurSyntaxique() {
        super();
    }

    /**
     * Constructor which sets the default scanner.
     */
    public AnalyseurSyntaxique(java_cup.runtime.Scanner s) {
        super(s);
    }

    /**
     * Constructor which sets the default scanner.
     */
    public AnalyseurSyntaxique(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {
        super(s, sf);
    }

    /**
     * Production table.
     */
    protected static final short[][] _production_table =
            unpackFromStrings(new String[]{
                    "\000\026\000\002\002\004\000\002\002\007\000\002\002" +
                            "\005\000\002\002\011\000\002\004\004\000\002\004\003" +
                            "\000\002\003\004\000\002\003\003\000\002\005\004\000" +
                            "\002\005\003\000\002\011\005\000\002\011\006\000\002" +
                            "\011\005\000\002\012\003\000\002\012\003\000\002\012" +
                            "\003\000\002\012\005\000\002\013\003\000\002\013\003" +
                            "\000\002\006\005\000\002\007\004\000\002\010\010"});

    /**
     * Access to production table.
     */
    public short[][] production_table() {
        return _production_table;
    }

    /**
     * Parse-action table.
     */
    protected static final short[][] _action_table =
            unpackFromStrings(new String[]{
                    "\000\062\000\006\004\004\006\006\001\002\000\010\010" +
                            "\035\015\033\016\032\001\002\000\004\002\062\001\002" +
                            "\000\006\021\010\022\012\001\002\000\012\004\016\012" +
                            "\020\021\010\022\012\001\002\000\004\016\uffef\001\002" +
                            "\000\012\004\ufffc\012\ufffc\021\ufffc\022\ufffc\001\002\000" +
                            "\004\016\ufff0\001\002\000\004\016\014\001\002\000\004" +
                            "\007\015\001\002\000\012\004\uffee\012\uffee\021\uffee\022" +
                            "\uffee\001\002\000\010\010\035\015\033\016\032\001\002" +
                            "\000\012\004\ufffd\012\ufffd\021\ufffd\022\ufffd\001\002\000" +
                            "\006\021\010\022\012\001\002\000\010\004\ufff8\021\ufff8" +
                            "\022\ufff8\001\002\000\010\004\054\021\010\022\012\001" +
                            "\002\000\004\016\024\001\002\000\004\013\026\001\002" +
                            "\000\004\004\030\001\002\000\004\014\027\001\002\000" +
                            "\004\004\uffed\001\002\000\010\010\035\015\033\016\032" +
                            "\001\002\000\012\005\ufffa\010\ufffa\015\ufffa\016\ufffa\001" +
                            "\002\000\004\011\051\001\002\000\010\016\037\017\036" +
                            "\020\040\001\002\000\012\005\046\010\035\015\033\016" +
                            "\032\001\002\000\010\016\037\017\036\020\040\001\002" +
                            "\000\004\007\ufff4\001\002\000\006\007\ufff2\013\043\001" +
                            "\002\000\004\007\ufff3\001\002\000\004\007\042\001\002" +
                            "\000\012\005\ufff7\010\ufff7\015\ufff7\016\ufff7\001\002\000" +
                            "\004\014\044\001\002\000\004\007\ufff1\001\002\000\012" +
                            "\005\ufffb\010\ufffb\015\ufffb\016\ufffb\001\002\000\010\004" +
                            "\uffec\021\uffec\022\uffec\001\002\000\004\007\050\001\002" +
                            "\000\012\005\ufff5\010\ufff5\015\ufff5\016\ufff5\001\002\000" +
                            "\010\016\037\017\036\020\040\001\002\000\004\007\053" +
                            "\001\002\000\012\005\ufff6\010\ufff6\015\ufff6\016\ufff6\001" +
                            "\002\000\010\010\035\015\033\016\032\001\002\000\010" +
                            "\004\ufff9\021\ufff9\022\ufff9\001\002\000\012\005\057\010" +
                            "\035\015\033\016\032\001\002\000\004\002\ufffe\001\002" +
                            "\000\012\005\061\010\035\015\033\016\032\001\002\000" +
                            "\004\002\000\001\002\000\004\002\001\001\002\000\012" +
                            "\005\064\010\035\015\033\016\032\001\002\000\004\002" +
                            "\uffff\001\002"});

    /**
     * Access to parse-action table.
     */
    public short[][] action_table() {
        return _action_table;
    }

    /**
     * <code>reduce_goto</code> table.
     */
    protected static final short[][] _reduce_table =
            unpackFromStrings(new String[]{
                    "\000\062\000\004\002\004\001\001\000\006\003\062\011" +
                            "\030\001\001\000\002\001\001\000\010\004\006\006\010" +
                            "\013\012\001\001\000\006\006\016\013\012\001\001\000" +
                            "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
                            "\001\001\000\002\001\001\000\002\001\001\000\006\003" +
                            "\057\011\030\001\001\000\002\001\001\000\010\005\021" +
                            "\010\020\013\022\001\001\000\002\001\001\000\006\010" +
                            "\054\013\022\001\001\000\002\001\001\000\004\007\024" +
                            "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
                            "\001\000\006\003\033\011\030\001\001\000\002\001\001" +
                            "\000\002\001\001\000\004\012\046\001\001\000\004\011" +
                            "\044\001\001\000\004\012\040\001\001\000\002\001\001" +
                            "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
                            "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
                            "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
                            "\001\000\004\012\051\001\001\000\002\001\001\000\002" +
                            "\001\001\000\006\003\055\011\030\001\001\000\002\001" +
                            "\001\000\004\011\044\001\001\000\002\001\001\000\004" +
                            "\011\044\001\001\000\002\001\001\000\002\001\001\000" +
                            "\004\011\044\001\001\000\002\001\001"});

    /**
     * Access to <code>reduce_goto</code> table.
     */
    public short[][] reduce_table() {
        return _reduce_table;
    }

    /**
     * Instance of action encapsulation class.
     */
    protected CUP$AnalyseurSyntaxique$actions action_obj;

    /**
     * Action encapsulation object initializer.
     */
    protected void init_actions() {
        action_obj = new CUP$AnalyseurSyntaxique$actions(this);
    }

    /**
     * Invoke a user supplied parse action.
     */
    public java_cup.runtime.Symbol do_action(
            int act_num,
            java_cup.runtime.lr_parser parser,
            java.util.Stack stack,
            int top)
            throws java.lang.Exception {
        /* call code in generated class */
        return action_obj.CUP$AnalyseurSyntaxique$do_action(act_num, parser, stack, top);
    }

    /**
     * Indicates start state.
     */
    public int start_state() {
        return 0;
    }

    /**
     * Indicates start production.
     */
    public int start_production() {
        return 0;
    }

    /**
     * <code>EOF</code> Symbol index.
     */
    public int EOF_sym() {
        return 0;
    }

    /**
     * <code>error</code> Symbol index.
     */
    public int error_sym() {
        return 1;
    }


    public void report_error(String message, Object info) {

        HashMap<Integer, String> lesTerminaux = new HashMap<>();

        lesTerminaux.put(new Integer(CodesLexicaux.DEBUT), "debut");
        lesTerminaux.put(new Integer(CodesLexicaux.FIN), "fin");
        lesTerminaux.put(new Integer(CodesLexicaux.POINTVIRGULE), ";");

        StringBuffer m = new StringBuffer();

        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);

            if (s.left >= 0) {
                m.append("\tligne : " + (s.left + 1));
                if (s.right >= 0)
                    m.append(" colonne : " + (s.right + 1));
            }

            if (s.value != null) {
                lesTerminaux.put(CodesLexicaux.CSTENTIERE, "" + s.value);
            }

            if (lesTerminaux.containsKey(new Integer(s.sym))) {
                m.append(" dernier token lu : " + lesTerminaux.get(new Integer(s.sym)));
            } else {
                m.append(" expression non terminée");
            }

        }
        throw new AnalyseSyntaxiqueException("" + m);
    }

    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
    }

}

/**
 * Cup generated class to encapsulate user supplied action code.
 */
class CUP$AnalyseurSyntaxique$actions {


    private final AnalyseurSyntaxique parser;

    /**
     * Constructor
     */
    CUP$AnalyseurSyntaxique$actions(AnalyseurSyntaxique parser) {
        this.parser = parser;
    }

    /**
     * Method with the actual generated action code.
     */
    public final java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$do_action(
            int CUP$AnalyseurSyntaxique$act_num,
            java_cup.runtime.lr_parser CUP$AnalyseurSyntaxique$parser,
            java.util.Stack CUP$AnalyseurSyntaxique$stack,
            int CUP$AnalyseurSyntaxique$top)
            throws java.lang.Exception {
        /* Symbol object for return from actions */
        java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$result;

        /* select the action based on the action number */
        switch (CUP$AnalyseurSyntaxique$act_num) {
            /*. . . . . . . . . . . . . . . . . . . .*/
            case 21: // FONC ::= TYPE IDF PARAM DEBUT LINST FIN
            {
                ArbreAbstrait RESULT = null;
                int tleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).left;
                int tright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).right;
                String t = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).value;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 4)).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 4)).right;
                String i = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 4)).value;
                int pleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).left;
                int pright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).right;
                ArbreAbstrait p = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).value;
                int lileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int liright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait li = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;

                try {
                    TDS.getInstance().ajouter(new Entree(i, "fonction"), new Symbole(t));
                    GestionnaireFonctions.getInstance().ajouter(new Fonction(ileft + 1, i, li));
                } catch (DoubleDeclarationException e) {
                    GestionnaireFonctions.getInstance().ajouter(new Fonction(ileft + 1, i + "ERREUR" + (ileft + 1), li));
                    StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), ileft + 1, "DECLARATION"));
                }

                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("FONC", 6, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 20: // PARAM ::= PAROUVRANTE PARFERMANTE
            {
                ArbreAbstrait RESULT = null;

                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PARAM", 5, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 19: // DECL ::= TYPE IDF POINTVIRGULE
            {
                ArbreAbstrait RESULT = null;
                int tleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).left;
                int tright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).right;
                String t = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).value;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                String i = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;

                try {
                    TDS.getInstance().ajouter(new Entree(i, "variable"), new Symbole(t));
                } catch (DoubleDeclarationException e) {
                    StockageErreurs.getInstance().ajouter(new Erreur(e.getMessage(), ileft + 1, "DECLARATION"));
                }

                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL", 4, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 18: // TYPE ::= BOOLEEN
            {
                String RESULT = null;
                RESULT = "booleen";
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("TYPE", 9, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 17: // TYPE ::= ENTIER
            {
                String RESULT = null;
                RESULT = "entier";
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("TYPE", 9, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 16: // EXP ::= IDF PAROUVRANTE PARFERMANTE
            {
                Expression RESULT = null;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).right;
                String i = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)).value;
                RESULT = new AppelFonction(ileft + 1, i);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP", 8, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 15: // EXP ::= IDF
            {
                Expression RESULT = null;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                String i = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                RESULT = new Idf(i, ileft + 1);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP", 8, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 14: // EXP ::= CSTBOOLEENNE
            {
                Expression RESULT = null;
                int cleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int cright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                String c = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                RESULT = new ConstanteBooleenne(c, cleft + 1);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP", 8, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 13: // EXP ::= CSTENTIERE
            {
                Expression RESULT = null;
                int cleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int cright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                String c = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                RESULT = new ConstanteEntiere(c, cleft + 1);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP", 8, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 12: // INST ::= RETOURNE EXP POINTVIRGULE
            {
                Instruction RESULT = null;
                int eleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                Expression e = (Expression) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = new Retourne(eleft + 1, e);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST", 7, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 11: // INST ::= IDF EGAL EXP POINTVIRGULE
            {
                Instruction RESULT = null;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).right;
                String i = (String) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).value;
                int eleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                Expression e = (Expression) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = new Affect(new Idf(i, ileft + 1), e, eleft + 1);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST", 7, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 10: // INST ::= ECRIRE EXP POINTVIRGULE
            {
                Instruction RESULT = null;
                int eleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int eright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                Expression e = (Expression) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = new Ecrire(e, eleft + 1);
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST", 7, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 9: // LFONC ::= FONC
            {
                ArbreAbstrait RESULT = null;
                int fleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int fright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                ArbreAbstrait f = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                BlocDInstructions b = new BlocDInstructions(fleft + 1);
                b.ajouter(f);
                RESULT = b;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LFONC", 3, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 8: // LFONC ::= LFONC FONC
            {
                ArbreAbstrait RESULT = null;
                int lfleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int lfright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait lf = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                int fleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int fright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                ArbreAbstrait f = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                ((BlocDInstructions) lf).ajouter(f);
                RESULT = lf;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LFONC", 3, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 7: // LINST ::= INST
            {
                ArbreAbstrait RESULT = null;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                Instruction i = (Instruction) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                BlocDInstructions b = new BlocDInstructions(ileft + 1);
                b.ajouter(i);
                RESULT = b;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST", 1, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 6: // LINST ::= LINST INST
            {
                ArbreAbstrait RESULT = null;
                int lileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int liright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait li = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                int ileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int iright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                Instruction i = (Instruction) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                ((BlocDInstructions) li).ajouter(i);
                RESULT = li;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST", 1, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 5: // LDECL ::= DECL
            {
                ArbreAbstrait RESULT = null;
                int dleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int dright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                ArbreAbstrait d = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                BlocDInstructions b = new BlocDInstructions(dleft + 1);
                b.ajouter(d);
                RESULT = b;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LDECL", 2, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 4: // LDECL ::= LDECL DECL
            {
                ArbreAbstrait RESULT = null;
                int ldleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int ldright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait ld = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                int dleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).left;
                int dright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).right;
                ArbreAbstrait d = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
                ((BlocDInstructions) ld).ajouter(d);
                RESULT = ld;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LDECL", 2, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 3: // PROG ::= VARIABLES LDECL FONCTIONS LFONC DEBUT LINST FIN
            {
                ArbreAbstrait RESULT = null;
                int ldleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).left;
                int ldright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).right;
                ArbreAbstrait ld = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 5)).value;
                int lfleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).left;
                int lfright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).right;
                ArbreAbstrait lf = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).value;
                int lileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int liright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait li = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = li;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG", 0, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 6)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 2: // PROG ::= DEBUT LINST FIN
            {
                ArbreAbstrait RESULT = null;
                int lileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int liright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait li = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = li;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG", 0, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 2)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 1: // PROG ::= VARIABLES LDECL DEBUT LINST FIN
            {
                ArbreAbstrait RESULT = null;
                int ldleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).left;
                int ldright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).right;
                ArbreAbstrait ld = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 3)).value;
                int lileft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int liright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait li = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = li;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG", 0, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 4)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            return CUP$AnalyseurSyntaxique$result;

            /*. . . . . . . . . . . . . . . . . . . .*/
            case 0: // $START ::= PROG EOF
            {
                Object RESULT = null;
                int start_valleft = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).left;
                int start_valright = ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).right;
                ArbreAbstrait start_val = (ArbreAbstrait) ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)).value;
                RESULT = start_val;
                CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("$START", 0, ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top - 1)), ((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
            /* ACCEPT */
            CUP$AnalyseurSyntaxique$parser.done_parsing();
            return CUP$AnalyseurSyntaxique$result;

            /* . . . . . .*/
            default:
                throw new Exception(
                        "Invalid action number found in internal parse table");

        }
    }
}

