package io.github.anonymous123_code.chasmlang.type;

import org.jetbrains.annotations.NotNull;

public class AnyType implements Type{
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        return true;
    }

    @Override
    public @NotNull String asString() {
        return "Any";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AnyType;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
