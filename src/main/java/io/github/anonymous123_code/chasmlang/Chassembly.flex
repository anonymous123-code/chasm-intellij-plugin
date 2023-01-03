package io.github.anonymous123_code.chasmlang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.github.anonymous123_code.chasmlang.psi.ChassemblyTypes.*;

%%


%class ChassemblyLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%eof{  return EOF;
%eof}

EOL = \R
WHITE_SPACE = \s+
LineComment = "//" ([^\n])*
InlineComment = "/*" ([^*] | ("*" [^/]))* "*/"
Sign = "+" | "-"
Digit = [0-9]
HexDigit = {Digit} | [a-fA-F]
SpecialFloatLiteral = ({Sign})? ("NaN" | "Infinity")
DecFloatLiteral = ({Sign})? ((({Digit})+ "." ({Digit})*) | ("." ({Digit})+)) (("e" | "E") ({Sign})? ({Digit})+)?
StringChar = [^\"\\] | (\\\") | (\\\\)
Char = [^\'\\] | "\\'" | "\\\\"
ReferenceLiteralChar = [^\`\\] | "\\`" | "\\\\"


%%
// SKIP
<YYINITIAL> {
    {WHITE_SPACE}                                   { return WHITE_SPACE; }
    {EOL}                                           { return WHITE_SPACE; }
    {LineComment}                                   { return COMMENT; }
    {InlineComment}                                 { return COMMENT; }
}

// LiteralTokens
<YYINITIAL> {
    "null"                                          { return NULL_LITERAL; }
    "true" | false                                  { return BOOLEAN_LITERAL; }
    ({Sign})? ({Digit})+                            { return DEC_INTEGER_LITERAL; }
    ({Sign})? "0x" ({HexDigit})+                    { return HEX_INTEGER_LITERAL; }
    ({Sign})? "0b" [01]+                            { return BIN_INTGER_LITERAL; }
    ({SpecialFloatLiteral}) | ({DecFloatLiteral})   { return FLOAT_LITERAL; }
    "\"" ({StringChar})* "\""                       { return STRING_LITERAL; }
    "'" {Char} "'"                                  { return CHAR_LITERAL; }
    "`" ({ReferenceLiteralChar})* "`"               { return REFERENCE_LITERAL; }
}

// Identifier
<YYINITIAL> {
    [_a-zA-Z]([_a-zA-Z0-9])*                        { return IDENTIFIER; }
}

// Operators
<YYINITIAL> {
    "+"                                             { return PLUS_OPERATOR; }
    "-"                                             { return MINUS_OPERATOR; }
    "!"                                             { return NOT_OPERATOR; }
    "~"                                             { return INVERT_OPERATOR; }
    "*"                                             { return MULTIPLY_OPERATOR; }
    "/"                                             { return DIVIDE_OPERATOR; }
    "%"                                             { return MODULO_OPERATOR; }
    "<<"                                            { return SHIFT_LEFT_OPERATOR; }
    ">>"                                            { return SHIFT_RIGHT_OPERATOR; }
    ">>>"                                           { return SHIFT_RIGHT_UNSIGNED_OPERATOR; }
    "<"                                             { return LESS_THAN_OPERATOR; }
    "<="                                            { return LESS_THAN_OR_EQUAL_OPERATOR; }
    ">"                                             { return GREATER_THAN_OPERATOR; }
    ">="                                            { return GREATER_THAN_OR_EQUAL_OPERATOR; }
    "="                                             { return EQUAL_OPERATOR; }
    "!="                                            { return NOT_EQUAL_OPERATOR; }
    "&"                                             { return BITWISE_AND_OPERATOR; }
    "^"                                             { return BITWISE_XOR_OPERATOR; }
    "|"                                             { return BITWISE_OR_OPERATOR; }
    "&&"                                            { return BOOLEAN_AND_OPERATOR; }
    "||"                                            { return BOOLEAN_OR_OPERATOR; }
    "?"                                             { return TERNARY_OPERATOR; }
    "->"                                            { return LAMBDA_OPERATOR; }
}

// Punctuation
<YYINITIAL> {
    ","                                             { return COMMA; }
    "."                                             { return DOT; }
    ":"                                             { return COLON; }
    "("                                             { return LEFT_PARANTHESES; }
    ")"                                             { return RIGHT_PARANTHESES; }
    "["                                             { return LEFT_BRACKETS; }
    "]"                                             { return RIGHT_BRACKETS; }
    "{"                                             { return LEFT_BRACES; }
    "}"                                             { return RIGHT_BRACES; }
}

[^] { return BAD_CHARACTER; }
