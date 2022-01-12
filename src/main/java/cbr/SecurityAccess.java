package cbr;

import cbr.object.Access;
import cbr.scanner.annotation.ClassAnnotationScanner;
import cbr.scanner.annotation.MethodAnnotationScanner;
import cbr.scanner.annotation.ParameterAnnotationScanner;
import org.objectweb.asm.ClassReader;

import java.io.FileInputStream;
import java.util.List;

public class SecurityAccess {

    public static List<Access> inspect(String name) {
        try (var in = new FileInputStream(name)) {
            var reader = new ClassReader(in);

            // аннотации над классом
            new ClassAnnotationScanner(reader).getResult().forEach(System.out::println);

            // аннотации над методом
            new MethodAnnotationScanner(reader).getResult().forEach(System.out::println);

            // аннотации в параметрах метода
            new ParameterAnnotationScanner(reader).getResult().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
