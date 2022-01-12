package cbr;

import cbr.object.Access;
import cbr.object.Field;
import cbr.object.Invoke;
import cbr.object.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SecurityFilters<MESSAGE, INSNS> {
    private final List<Function<INSNS, Optional<MESSAGE>>> validators;

    private SecurityFilters(List<Function<INSNS, Optional<MESSAGE>>> validators) {
        List<Function<INSNS, Optional<MESSAGE>>> validators00 = new ArrayList<>();
        if (validators != null) {
            validators00.addAll(validators);
        }
        this.validators = Collections.unmodifiableList(validators00);
    }

    public void validate(List<INSNS> insns) {
        for (var insn : insns) {
            for (var f : validators) {
                f.apply(insn).ifPresent(message -> {
                    System.out.println("insns:   " + insn.toString());
                    System.out.println("message: " + message);
                    System.out.println();
                });
            }
        }
    }

    public static Builder<String, Access> create() {
        return new Builder<>();
    }

    public static class Builder<MESSAGE, INSNS> {
        private final List<Function<INSNS, Optional<MESSAGE>>> validators = new ArrayList<>();

        public Builder<MESSAGE, INSNS> deny(Consumer<DenyBuilder<MESSAGE, INSNS>> conf) {
            if (conf == null) throw new IllegalArgumentException("conf==null");
            conf.accept(new DenyBuilder<>(this));
            return this;
        }

        public SecurityFilters<MESSAGE, INSNS> build() {
            return new SecurityFilters<>(validators);
        }
    }

    public static class DenyBuilder<MESSAGE, INSNS> extends PredicateBuilder<MESSAGE, INSNS> {

        public DenyBuilder(Builder<MESSAGE, INSNS> builder) {
            super(builder);
        }

        @Override
        protected void append(Function<INSNS, Optional<MESSAGE>> filter) {
            builder.validators.add(ev -> {
                var res = filter.apply(ev);
                return res;
            });
        }
    }

    public abstract static class PredicateBuilder<MESSAGE, INSNS> {
        public final Builder<MESSAGE, INSNS> builder;

        public PredicateBuilder(Builder<MESSAGE, INSNS> builder) {
            this.builder = builder;
        }

        protected abstract void append(Function<INSNS, Optional<MESSAGE>> filter);

        public void method(MESSAGE message, Predicate<Method> filter) {
            if (filter == null) throw new IllegalArgumentException("filter == null");
            append(ev -> {
                if (ev instanceof Method) {
                    return filter.test((Method) ev) ? Optional.of(message) : Optional.empty();
                }
                return Optional.empty();
            });
        }

        public void field(MESSAGE message, Predicate<Field> filter) {
            if (filter == null) throw new IllegalArgumentException("filter == null");
            append(ev -> {
                if (ev instanceof Field) {
                    return filter.test((Field) ev) ? Optional.of(message) : Optional.empty();
                }
                return Optional.empty();
            });
        }

        public void invoke(MESSAGE message, Predicate<Invoke> filter) {
            if (filter == null) throw new IllegalArgumentException("filter == null");
            append(ev -> {
                if (ev instanceof Invoke) {
                    return filter.test((Invoke) ev) ? Optional.of(message) : Optional.empty();
                }
                return Optional.empty();
            });
        }
    }
}
