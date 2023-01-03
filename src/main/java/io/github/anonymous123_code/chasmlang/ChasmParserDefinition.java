package io.github.anonymous123_code.chasmlang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.github.anonymous123_code.chasmlang.parser.ChassemblyParser;
import io.github.anonymous123_code.chasmlang.psi.ChasmFile;
import io.github.anonymous123_code.chasmlang.psi.ChassemblyTokenSets;
import io.github.anonymous123_code.chasmlang.psi.ChassemblyTypes;
import org.jetbrains.annotations.NotNull;

public class ChasmParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(ChassemblyLanguage.INSTANCE);
    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ChassemblyLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new ChassemblyParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return ChassemblyTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return ChassemblyTokenSets.STRING_LITERALS;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return ChassemblyTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ChasmFile(viewProvider);
    }
}
