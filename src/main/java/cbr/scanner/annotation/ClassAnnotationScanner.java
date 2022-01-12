package cbr.scanner.annotation;

import cbr.object.Accumulator;
import cbr.object.annotation.ClassAnnotation;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassAnnotationScanner extends Accumulator<ClassAnnotation> {
    private final ClassReader reader;

    public ClassAnnotationScanner(ClassReader reader) {
        this.reader = reader;
        this.reader.accept(new Scanner(Opcodes.ASM9), 0);
    }

    private class Scanner extends ClassVisitor {
        public Scanner(int api) {
            super(api);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            return new AnnotationScanner(descriptor, visible);
        }
    }

    private class AnnotationScanner extends AnnotationVisitor {
        private final String descriptor;
        private final boolean visible;

        public AnnotationScanner(String descriptor, boolean visible) {
            super(Opcodes.ASM9);
            this.descriptor = descriptor;
            this.visible = visible;
        }

        @Override
        public void visit(String name, Object value) {
            add(new ClassAnnotation(reader.getClassName(), descriptor, visible, name, value));
        }
    }
}
