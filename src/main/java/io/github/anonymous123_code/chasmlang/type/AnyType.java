package io.github.anonymous123_code.chasmlang.type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AnyType implements Type{
    @Contract(pure = true)
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        return true;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String asString() {
        return "Any";
    }

    @Contract(pure = true)
    @Override
    public boolean equals(Object obj) {
        return obj instanceof AnyType;
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return 0;
    }
}
