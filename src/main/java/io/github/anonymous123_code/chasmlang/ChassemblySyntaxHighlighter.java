package io.github.anonymous123_code.chasmlang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import io.github.anonymous123_code.chasmlang.psi.ChassemblyTokenSets;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ChassemblySyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey OPERATOR =
        createTextAttributesKey("CHASSEMBLY.OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("CHASSEMBLY.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("CHASSEMBLY.NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("CHASSEMBLY.STRING", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey FUNCTION_CALL =
            createTextAttributesKey("CHASSEMBLY.FUNCTION_CAL", DefaultLanguageHighlighterColors.FUNCTION_CALL);

    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("CHASSEMBLY.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("CHASSEMBLY.BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey PARENTHESES =
            createTextAttributesKey("CHASSEMBLY.PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);

    public static final TextAttributesKey DOT =
            createTextAttributesKey("CHASSEMBlY.DOT", DefaultLanguageHighlighterColors.DOT);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("CHASSEMBlY.COMMA", DefaultLanguageHighlighterColors.COMMA);

    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("CHASSEMBLY.LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("CHASSEMBLY.BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("CHASSEMBLY.BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};

    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};

    private static final TextAttributesKey[] FUNCTION_CALL_KEYS = new TextAttributesKey[]{FUNCTION_CALL};

    private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};
    private static final TextAttributesKey[] BRACES_KEYS = new TextAttributesKey[]{BRACES};
    private static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{PARENTHESES};

    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] DOT_KEYS = new TextAttributesKey[]{DOT};

    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ChassemblyLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (ChassemblyTokenSets.OPERATORS.contains(tokenType)) {
            return OPERATOR_KEYS;
        }

        if (ChassemblyTokenSets.BRACKETS.contains(tokenType)) {
            return BRACKETS_KEYS;
        }
        if (ChassemblyTokenSets.BRACES.contains(tokenType)) {
            return BRACES_KEYS;
        }
        if (ChassemblyTokenSets.PARENTHESES.contains(tokenType)) {
            return PARENTHESES_KEYS;
        }

        if (ChassemblyTokenSets.COMMAS.contains(tokenType)) {
            return COMMA_KEYS;
        }
        if (ChassemblyTokenSets.DOTS.contains(tokenType)) {
            return DOT_KEYS;
        }

        if (ChassemblyTokenSets.KEYWORDS.contains(tokenType)) {
            return KEYWORD_KEYS;
        }
        if (ChassemblyTokenSets.STRING_LITERALS.contains(tokenType)) {
            return STRING_KEYS;
        }
        if (ChassemblyTokenSets.NUMBERS.contains(tokenType)) {
            return NUMBER_KEYS;
        }

        if (ChassemblyTokenSets.LINE_COMMENTS.contains(tokenType)) {
            return LINE_COMMENT_KEYS;
        }
        if (ChassemblyTokenSets.BLOCK_COMMENTS.contains(tokenType)) {
            return BLOCK_COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}
