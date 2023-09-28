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
//InputCharacter = [^\r\n]
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
OpenLlave = "{"
CloseLlave = "}"
PyC = ";"
Coma = ","
Mayor = ">"
Menor = "<"
Menoroigual = "<="
Mayoroigual = ">="
Igual = "=="

Letter = [a-zA-Z]
Digit = [0-9]


While = "while"
If = "if"
Else = "else"

Float = "float"
Int = "int"
String = "string"

And = "&&" | "and"
Or = "||" | "or"
Not = "!!" | "not"

Firstindexof = "firstindexof"
Timer = "timer"
Read = "read"
Write = "write"
Init = "init"

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+

CaracteresAdmitidos=@|\?|\"|\.|\,|\+|\t|\n|\/|\_|\:|\;|¿|\*|{Letter}|{Digit}
CualquierCaracter={CaracteresAdmitidos}|{WhiteSpace}
Comentario =  "#""/"{CualquierCaracter}*"/""#"

FloatConstant = {Digit}*"."{Digit}+ | {Digit}+"."{Digit}*
StringConstant = \"{CualquierCaracter}*\"

%%

/* keywords */

<YYINITIAL> {
   /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {And}                                     { return symbol(ParserSym.AND); }
  {Or}                                      { return symbol(ParserSym.OR); }
  {Not}                                     { return symbol(ParserSym.NOT); }
  {OpenCorchete}                            { return symbol(ParserSym.OPEN_CORCHETE); }
  {CloseCorchete}                           { return symbol(ParserSym.CLOSE_CORCHETE); }
  {OpenLlave}                               { return symbol(ParserSym.OPEN_LLAVE); }
  {CloseLlave}                              { return symbol(ParserSym.CLOSE_LLAVE); }
  {PyC}                                     { return symbol(ParserSym.PYC); }
  {Coma}                                    { return symbol(ParserSym.COMA); }

  /* comparers */
  {Mayor}                                   { return symbol(ParserSym.MAYOR); }
  {Menor}                                   { return symbol(ParserSym.MENOR); }
  {Menoroigual}                             { return symbol(ParserSym.MENOROIGUAL); }
  {Mayoroigual}                             { return symbol(ParserSym.MAYOROIGUAL); }
  {Igual}                                   { return symbol(ParserSym.IGUAL); }
  /* palabras reservadas */
  {While}                                   { return symbol(ParserSym.WHILE); }
  {If}                                      { return symbol(ParserSym.IF); }
  {Else}                                    { return symbol(ParserSym.ELSE); }
  {Float}                                   { return symbol(ParserSym.FLOAT); }
  {Int}                                     { return symbol(ParserSym.INT); }
  {String}                                  { return symbol(ParserSym.STRING); }
  {Timer}                                   { return symbol(ParserSym.TIMER); }
  {Firstindexof}                            { return symbol(ParserSym.FIRSTINDEXOF); }
  {Read}                                    { return symbol(ParserSym.READ); }
  {Write}                                   { return symbol(ParserSym.WRITE); }
  {Init}                                    { return symbol(ParserSym.INIT); }

  /* Constants */
  {IntegerConstant}                         { if(Integer.parseInt(yytext())>32767){System.out.println("constante fuera de rango");throw new InvalidIntegerException(yytext());}else{return symbol(ParserSym.INTEGER_CONSTANT, yytext());} }
  {FloatConstant}                           { if(Float.parseFloat(yytext())>3.40282347e+38F) {System.out.println("constante fuera de rango");throw new NumberFormatException(yytext());}else{return symbol(ParserSym.FLOAT_CONSTANT, yytext());} }
  {StringConstant}                          { if((yytext().length()-2)>40){System.out.println("constante fuera de cota");throw new InvalidLengthException(yytext());}else{return symbol(ParserSym.STRING_CONSTANT, yytext());} }

 /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }

  /* whitespace */
  {WhiteSpace}                              { /* ignore */ }

  /* comentarios */
  {Comentario}                              { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
