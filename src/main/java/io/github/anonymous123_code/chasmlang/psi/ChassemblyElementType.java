package io.github.anonymous123_code.chasmlang.psi;

import com.intellij.psi.tree.IElementType;
import io.github.anonymous123_code.chasmlang.ChassemblyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ChassemblyElementType extends IElementType {
    public ChassemblyElementType(@NonNls @NotNull String debugName) {
        super(debugName, ChassemblyLanguage.INSTANCE);
    }
}
