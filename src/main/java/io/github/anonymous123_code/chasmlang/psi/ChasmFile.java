package io.github.anonymous123_code.chasmlang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import io.github.anonymous123_code.chasmlang.ChasmFileType;
import io.github.anonymous123_code.chasmlang.ChassemblyLanguage;
import org.jetbrains.annotations.NotNull;

public class ChasmFile extends PsiFileBase {
    public ChasmFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ChassemblyLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return ChasmFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Chasm File";
    }
}
