package io.github.anonymous123_code.chasmlang.type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FunctionType implements Type {
    private final Type argType;
    private final Type resultType;

    public FunctionType(Type argType, Type resultType) {
        this.argType = argType;
        this.resultType = resultType;
    }

    @Contract(pure = true)
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        return Type.mayServeAs(this, other) || (
                other instanceof FunctionType &&
                ((FunctionType) other).argType.mayServeAs(this.argType) &&
                this.resultType.mayServeAs(((FunctionType) other).resultType));
    }

    @Contract(pure = true)
    @Override
    public @NotNull String asString() {
        return argType.asString() + "->" + resultType.asString();
    }

    @Contract(pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionType)) return false;
        FunctionType that = (FunctionType) o;
        return argType.equals(that.argType) && resultType.equals(that.resultType);
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return Objects.hash(argType, resultType);
    }
}
