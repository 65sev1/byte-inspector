package cbr.scanner.annotation;

import cbr.object.Accumulator;
import cbr.object.annotation.MethodAnnotation;
import org.objectweb.asm.*;

public class MethodAnnotationScanner extends Accumulator<MethodAnnotation> {
    private final ClassReader reader;

    public MethodAnnotationScanner(ClassReader reader) {
        this.reader = reader;
        this.reader.accept(new Scanner(Opcodes.ASM9), 0);
    }

    private class Scanner extends ClassVisitor {
        public Scanner(int api) {
            super(api);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            return new MethodScanner(name);
        }
    }

    private class MethodScanner extends MethodVisitor {
        private final String methodName;

        public MethodScanner(String methodName) {
            super(Opcodes.ASM9);
            this.methodName = methodName;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            return new AnnotationScanner(methodName, descriptor, visible);
        }
    }

    private class AnnotationScanner extends AnnotationVisitor {
        private final String methodName;
        private final String descriptor;
        private final boolean visible;

        public AnnotationScanner(String methodName, String descriptor, boolean visible) {
            super(Opcodes.ASM9);
            this.methodName = methodName;
            this.descriptor = descriptor;
            this.visible = visible;
        }

        @Override
        public void visit(String name, Object value) {
            add(new MethodAnnotation(reader.getClassName(), methodName, descriptor, visible, name, value));
        }
    }
}
