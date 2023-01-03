package io.github.anonymous123_code.chasmlang;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import io.github.anonymous123_code.chasmlang.psi.ChassemblyTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChassemblyBraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {
        return new BracePair[]{
                new BracePair(ChassemblyTypes.LEFT_BRACES, ChassemblyTypes.RIGHT_BRACES, true),
                new BracePair(ChassemblyTypes.LEFT_BRACKETS, ChassemblyTypes.RIGHT_BRACKETS, false),
                new BracePair(ChassemblyTypes.LEFT_PARANTHESES, ChassemblyTypes.RIGHT_PARANTHESES, false),
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return 0;
    }
}
