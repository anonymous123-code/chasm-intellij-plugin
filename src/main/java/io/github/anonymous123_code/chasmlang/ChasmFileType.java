package io.github.anonymous123_code.chasmlang;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ChasmFileType extends LanguageFileType {
    public static final ChasmFileType INSTANCE = new ChasmFileType();
    private ChasmFileType() {
        super(ChassemblyLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Chasm Transformer";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Chasm Transformer file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "chasm";
    }

    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Text;
    }
}
