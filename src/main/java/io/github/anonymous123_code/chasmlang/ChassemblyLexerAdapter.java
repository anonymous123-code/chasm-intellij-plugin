package io.github.anonymous123_code.chasmlang;

import com.intellij.lexer.FlexAdapter;

public class ChassemblyLexerAdapter extends FlexAdapter {

    public ChassemblyLexerAdapter() {
        super(new ChassemblyLexer(null));
    }
}
