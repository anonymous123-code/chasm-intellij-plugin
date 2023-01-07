package io.github.anonymous123_code.chasmlang.type;

import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapType implements Type {
    private final HashMap<String, Type> types;

    public MapType(HashMap<String, Type> types) {
        this.types = types;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<LookupElement> getCompletions() {
        return Type.super.getCompletions();
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<LookupElement> getCompletions(@NotNull Type givenType) {
        return Type.super.getCompletions(givenType);
    }

    @Contract(pure = true)
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        if (Type.mayServeAs(this, other)) return true;
        if (!(other instanceof MapType)) return false;
        if (!(types.keySet().containsAll(((MapType) other).types.keySet()))) return false;
        for (Map.Entry<String, Type> entry : ((MapType) other).types.entrySet()) {
            if (!this.types.get(entry.getKey()).mayServeAs(entry.getValue())) return false;
        }
        return true;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String asString() {
        return types.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue().asString())
                .collect(Collectors.joining(", ", "{","}"));
    }

    @Contract(pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapType)) return false;
        MapType mapType = (MapType) o;
        return types.equals(mapType.types);
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return Objects.hash(types);
    }
}
