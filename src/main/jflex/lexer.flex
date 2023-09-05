package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
OpenCorchete = "["
CloseCorchete = "]"
OpenLlave ="{"
CloseLlave = "}"
PyC = ";"
Coma = ","
mayor=">"
menor="<"
menoroigual="<="
mayoroigual=">="
igual="=="

Letter = [a-zA-Z]
Digit = [0-9]


while = "while"
if = "if"
else = "else"

float = "float"
int = "int"
string = "string"

and="&&"
or="||"
not="!!"

firstindexof="firstindexof"
timer="timer"


WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+

CaracteresAdmitidos=@|\?|\"|\.|\,|\+|\t|\n|\/|\_|\:|\;|¿|\*|{Letter}|{Digit}
CualquierCaracter={CaracteresAdmitidos}|{WhiteSpace}
Comentario =  "#/"{CualquierCaracter}*"#/"

floatConstant = {Digit}*"."{Digit}+ | {Digit}+"."{Digit}*
stringConstant = \"{CualquierCaracter}*\"

%%

/* keywords */

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {and}                                     { return symbol(ParserSym.AND); }
  {or}                                      { return symbol(ParserSym.OR); }
  {not}                                     { return symbol(ParserSym.NOT); }
  {OpenCorchete}                            { return symbol(ParserSym.OPEN_CORCHETE); }
  {CloseCorchete}                           { return symbol(ParserSym.CLOSE_CORCHETE); }
  {OpenLlave}                               { return symbol(ParserSym.OPEN_LLAVE); }
  {CloseLlave}                              { return symbol(ParserSym.CLOSE_LLAVE); }
  {PyC}                                     { return symbol(ParserSym.PYC); }
  {Coma}                                    { return symbol(ParserSym.COMA); }

  /* comparers */
  {mayor}                                   { return symbol(ParserSym.MAYOR); }
  {menor}                                   { return symbol(ParserSym.MENOR); }
  {menoroigual}                             { return symbol(ParserSym.MENOROIGUAL); }
  {mayoroigual}                             { return symbol(ParserSym.MAYOROIGUAL); }
  {igual}                                   { return symbol(ParserSym.IGUAL); }

  /* palabras reservadas */
  {while}                                   { return symbol(ParserSym.WHILE); }
  {if}                                      { return symbol(ParserSym.IF); }
  {else}                                    { return symbol(ParserSym.ELSE); }
  {float}                                   { return symbol(ParserSym.FLOAT); }
  {int}                                     { return symbol(ParserSym.INT); }
  {string}                                  { return symbol(ParserSym.STRING); }
  {timer}                                   { return symbol(ParserSym.TIMER); }
  {firstindexof}                            { return symbol(ParserSym.FIRSTINDEXOF); }

  /* constantes */
  {IntegerConstant}                         { if(Parseint(Integer.ParseInt(yytext())>32767)){System.out.println("constante fuera de rango");throw new InvalidIntegerException(yytext());}else{return symbol(ParserSym.INTEGER_CONSTANT, yytext());} }
  {floatConstant}                           { if(Float.parseFloat(yytext())>3.40282347e+38F) {System.out.println("constante fuera de rango");throw new NumberFormatException(yytext());}else{return symbol(ParserSym.FLOAT_CONSTANT, yytext());} }
  {stringConstant}                          { if((yytext().length()-2)>40){System.out.println("constante fuera de cota");throw new InvalidLengthException(yytext());}else{return symbol(ParserSym.STRING_CONSTANT, yytext());} }

  /* whitespace */
  {WhiteSpace}                              { /* ignore */ }

  /* comentarios */
  {Comentario}                              { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
