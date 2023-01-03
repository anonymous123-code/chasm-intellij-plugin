package io.github.anonymous123_code.chasmlang;

import com.intellij.lang.Language;

public class ChassemblyLanguage extends Language {
    public static final ChassemblyLanguage INSTANCE = new ChassemblyLanguage();
    private ChassemblyLanguage() {
        super("Chassembly");
    }
}
