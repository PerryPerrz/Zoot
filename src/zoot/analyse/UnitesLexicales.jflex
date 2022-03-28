package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = (vrai|faux)
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
idf = [a-zA-Z_][a-zA-Z0-9\-_]*
commentaire = [/]{2}.*{finDeLigne}

%%
"//".*                                    { /* DO NOTHING */ }

"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }
"variables"            { return symbol(CodesLexicaux.VARIABLES); }
"fonctions"            { return symbol(CodesLexicaux.FONCTIONS); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
","                    { return symbol(CodesLexicaux.VIRGULE); }
"("                    { return symbol(CodesLexicaux.PAROUVRANTE); }
")"                    { return symbol(CodesLexicaux.PARFERMANTE); }
"retourne"             { return symbol(CodesLexicaux.RETOURNE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{csteB}                { return symbol(CodesLexicaux.CSTBOOLEENNE, yytext()); }

"entier"               { return symbol(CodesLexicaux.ENTIER); }

"booleen"              { return symbol(CodesLexicaux.BOOLEEN); }

"="                    { return symbol(CodesLexicaux.EGAL); }
"+"                    { return symbol(CodesLexicaux.PLUS); }
"*"                    { return symbol(CodesLexicaux.MULT); }
"-"                    { return symbol(CodesLexicaux.MOINS); }

"et"                   { return symbol(CodesLexicaux.ET); }
"ou"                   { return symbol(CodesLexicaux.OU); }

{idf}                  { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
{commentaire}          { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

