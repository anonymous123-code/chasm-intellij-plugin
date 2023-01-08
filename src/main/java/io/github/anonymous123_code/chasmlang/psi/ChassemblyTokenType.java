package io.github.anonymous123_code.chasmlang.psi;

import com.intellij.psi.tree.IElementType;
import io.github.anonymous123_code.chasmlang.ChassemblyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ChassemblyTokenType extends IElementType {
    public ChassemblyTokenType(@NonNls @NotNull String debugName) {
        super(debugName, ChassemblyLanguage.INSTANCE);
    }
}
