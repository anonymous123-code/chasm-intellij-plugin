package io.github.anonymous123_code.chasmlang.type;

import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Type {
    @Contract(pure = true)
    default @NotNull List<LookupElement> getCompletions() {
        return List.of();
    }
    @Contract(pure = true)
    default @NotNull List<LookupElement> getCompletions(@NotNull Type givenType) {
        return List.of();
    }

    /**
     * Should always return true if @Code{Type.mayServeAs(this,other)} returns true
     * @param other
     * @return
     */
    @Contract(pure = true)
    boolean mayServeAs(@NotNull Type other);

    @Contract(pure = true)
    static boolean mayServeAs(@NotNull Type el, @NotNull Type other) {
        return other instanceof AnyType;
    }

    @Contract(pure = true)
    @NotNull String asString();
}
