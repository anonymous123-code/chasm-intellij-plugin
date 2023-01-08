package io.github.anonymous123_code.chasmlang.type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListType implements Type{
    private final Type[] types;

    private ListType(@NotNull Type... types) {
        this.types = types;
    }

    @Contract(pure = true, value = "_->new")
    public ListType withAdded(ListType that) {
        that = that.asNormalList();
        ListType self = this.asNormalList();
        Type[] resultTypes = new Type[self.types.length + that.types.length];
        System.arraycopy(self.types, 0, resultTypes, 0, self.types.length);
        System.arraycopy(that.types, 0, resultTypes, self.types.length, that.types.length);
        return ListType.of(resultTypes);
    }

    @Contract(pure = true)
    public ListType asNormalList() {
        return this;
    }

    @Contract(pure = true)
    @Override
    public boolean mayServeAs(@NotNull Type other) {
        if (Type.mayServeAs(this, other)) return true;
        if (!(other instanceof ListType)) return false;
        if (((ListType) other).types.length != this.types.length) return false;

        Type[] types1 = ((ListType) other).types;
        for (int i = 0; i < this.types.length; i++) {
            if (!this.types[i].mayServeAs(types1[i])) return false;
        }

        return true;
    }

    @Contract(pure = true)
    public int getLength() {
        return this.types.length;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String asString() {
        return Arrays.stream(this.types)
                .map(Type::asString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static ListType of(boolean assumeDifferentType, Type... types) {
        if (assumeDifferentType) return new ListType(types);
        if (types.length == 0) return new SingleTypeListType(new AnyType(), 0);
        if (types.length == 1) return new SingleTypeListType(types[0], 1);
        Type firstType = types[0];
        for (Type type :
                types) {
            if (!type.mayServeAs(firstType)) return new ListType(types);
        }
        return new SingleTypeListType(firstType, types.length);
    }

    public static ListType of(Type... types) {
        return of(false, types);
    }

    @Contract(pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListType)) return false;
        ListType listType = (ListType) o;
        return Arrays.equals(types, listType.types);
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return Arrays.hashCode(types);
    }

    private static class SingleTypeListType extends ListType {
        private final Type contentType;
        private final int length;

        private SingleTypeListType (Type type, int length) {
            this.contentType = type;
            this.length = length;
        }

        @Contract(pure = true)
        @Override
        public int getLength() {
            return this.length;
        }

        @Contract(pure = true)
        @Override
        public boolean mayServeAs(@NotNull Type other) {
            return Type.mayServeAs(this, other) ||
                    (other instanceof SingleTypeListType &&
                    this.getLength() >= ((SingleTypeListType) other).getLength() &&
                    this.contentType.mayServeAs(((SingleTypeListType) other).contentType));
        }

        @Contract(pure = true)
        @Override
        public @NotNull String asString() {
            return this.contentType.asString() + "[" + length + "]";
        }

        @Contract(pure = true)
        @Override
        public ListType asNormalList() {
            Type[] list = new Type[length];
            Arrays.fill(list, contentType);
            return ListType.of(true, list);
        }

        @Contract(pure = true)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SingleTypeListType)) return false;
            SingleTypeListType that = (SingleTypeListType) o;
            return length == that.length && contentType.equals(that.contentType);
        }

        @Contract(pure = true)
        @Override
        public int hashCode() {
            return Objects.hash(contentType, length);
        }
    }
}
