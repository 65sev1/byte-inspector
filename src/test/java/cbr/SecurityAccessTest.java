package cbr;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

class SecurityAccessTest {

    @Test
    void inspect() throws InterruptedException {
        final Function<String, String> func = path -> {
            var access = SecurityAccess.inspect(path);
            var filters = SecurityFilters.create()
                    .deny(d -> {
                        d.field("System stdio", f -> f.getOwner().equals("java/lang/System") && f.isReadAccess());
                        d.field("Example regexp", f -> f.getName().matches("var\\d"));
                        d.field("qwe", f -> f.getName().equals("line"));
                    })
                    .deny(d -> d.invoke("Call system method", c -> c.getOwner().equals("java/lang/System") && c.getName().matches("exit")))
                    .deny(d -> d.method("method4 deny default access", m -> m.isDefault() && m.getName().equals("method4")))
                    .build();
            filters.validate(access);
            return "ok!";
        };
        SecurityAccessExecutor.create().execute(Runtime.getRuntime().availableProcessors(), func, "C:/soft/workspace/byte-inspector/target/");
    }
}
