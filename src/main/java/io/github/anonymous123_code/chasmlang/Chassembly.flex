package io.github.anonymous123_code.chasmlang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.github.anonymous123_code.chasmlang.psi.ChassemblyTypes.*;

%%

%public
%class ChassemblyLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

LINE_COMMENT="//"([^\n])*
BLOCK_COMMENT="/"[*]([^*]|([*][^/]))*[*]"/"
BOOLEAN_LITERAL=(true|false)
DEC_INTEGER_LITERAL=[+-]?[0-9]+
HEX_INTEGER_LITERAL=[+-]?0x[0-9a-fA-F]+
BIN_INTGER_LITERAL=[+-]?0b[01]+
FLOAT_LITERAL=[+-]?(NaN|Infinity|((([0-9]+\.[0-9]*)|(\.[0-9]+))([eE][+-]?[0-9]+)?))
STRING_LITERAL=\"([^\"\\]|(\\\")|(\\\\))*\"
CHAR_LITERAL='([^'\\]|(\')|(\\\\))'
REFERENCE_LITERAL=`([^`\\]|(\\`)|(\\\\))*`
IDENTIFIER=[_a-zA-Z][_a-zA-Z0-9]*

%%
<YYINITIAL> {
  {WHITE_SPACE}              { return WHITE_SPACE; }

  "+"                        { return PLUS_OPERATOR; }
  "-"                        { return MINUS_OPERATOR; }
  "!"                        { return NOT_OPERATOR; }
  "~"                        { return INVERT_OPERATOR; }
  "*"                        { return MULTIPLY_OPERATOR; }
  "/"                        { return DIVIDE_OPERATOR; }
  "%"                        { return MODULO_OPERATOR; }
  "<<"                       { return SHIFT_LEFT_OPERATOR; }
  ">>"                       { return SHIFT_RIGHT_OPERATOR; }
  ">>>"                      { return SHIFT_RIGHT_UNSIGNED_OPERATOR; }
  "<"                        { return LESS_THAN_OPERATOR; }
  "<="                       { return LESS_THAN_OR_EQUAL_OPERATOR; }
  ">"                        { return GREATER_THAN_OPERATOR; }
  ">="                       { return GREATER_THAN_OR_EQUAL_OPERATOR; }
  "="                        { return EQUAL_OPERATOR; }
  "!="                       { return NOT_EQUAL_OPERATOR; }
  "&"                        { return BITWISE_AND_OPERATOR; }
  "^"                        { return BITWISE_XOR_OPERATOR; }
  "|"                        { return BITWISE_OR_OPERATOR; }
  "&&"                       { return BOOLEAN_AND_OPERATOR; }
  "||"                       { return BOOLEAN_OR_OPERATOR; }
  "?"                        { return TERNARY_OPERATOR; }
  "->"                       { return LAMBDA_OPERATOR; }
  ","                        { return COMMA; }
  "."                        { return DOT; }
  ":"                        { return COLON; }
  "("                        { return LEFT_PARANTHESES; }
  ")"                        { return RIGHT_PARANTHESES; }
  "["                        { return LEFT_BRACKETS; }
  "]"                        { return RIGHT_BRACKETS; }
  "{"                        { return LEFT_BRACES; }
  "}"                        { return RIGHT_BRACES; }
  "null"                     { return NULL_LITERAL; }

  {LINE_COMMENT}             { return LINE_COMMENT; }
  {BLOCK_COMMENT}            { return BLOCK_COMMENT; }
  {BOOLEAN_LITERAL}          { return BOOLEAN_LITERAL; }
  {DEC_INTEGER_LITERAL}      { return DEC_INTEGER_LITERAL; }
  {HEX_INTEGER_LITERAL}      { return HEX_INTEGER_LITERAL; }
  {BIN_INTGER_LITERAL}       { return BIN_INTGER_LITERAL; }
  {FLOAT_LITERAL}            { return FLOAT_LITERAL; }
  {STRING_LITERAL}           { return STRING_LITERAL; }
  {CHAR_LITERAL}             { return CHAR_LITERAL; }
  {REFERENCE_LITERAL}        { return REFERENCE_LITERAL; }
  {IDENTIFIER}               { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
