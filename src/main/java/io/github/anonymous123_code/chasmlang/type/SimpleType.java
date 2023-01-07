package io.github.anonymous123_code.chasmlang.type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SimpleType implements Type{
    private final String name;
    public SimpleType(String name) {
        this.name = name;
    }
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        return Type.mayServeAs(this, other) || other.asString().equals(this.asString());
    }

    @Override
    public @NotNull String asString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleType)) return false;
        SimpleType that = (SimpleType) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Contract(pure = true, value = "->new")
    public static SimpleType string() {
        return new SimpleType("String");
    }
    @Contract(pure = true, value = "->new")
    public static SimpleType integer() {
        return new SimpleType("Integer");
    }
    @Contract(pure = true, value = "->new")
    public static SimpleType float_() {
        return new SimpleType("Float");
    }
    @Contract(pure = true, value = "->new")
    public static SimpleType bool() {
        return new SimpleType("Boolean");
    }
    @Contract(pure = true, value = "->new")
    public static SimpleType character() {
        return new SimpleType("Char");
    }
    public static SimpleType nil() {
        return new SimpleType("Null");
    }
}
