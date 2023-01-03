package io.github.anonymous123_code.chasmlang.psi;

import com.intellij.psi.tree.TokenSet;


public interface ChassemblyTokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(ChassemblyTypes.IDENTIFIER);
    TokenSet COMMENTS = TokenSet.create(ChassemblyTypes.COMMENT);
    TokenSet STRING_LITERALS = TokenSet.create(ChassemblyTypes.STRING_LITERAL);
}
