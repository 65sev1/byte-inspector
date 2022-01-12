package cbr.scanner.annotation;

import cbr.object.Accumulator;
import cbr.object.annotation.ParameterAnnotation;
import org.objectweb.asm.*;

public class ParameterAnnotationScanner extends Accumulator<ParameterAnnotation> {
    private final ClassReader reader;

    public ParameterAnnotationScanner(ClassReader reader) {
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
        public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
            return new AnnotationScanner(methodName, parameter, descriptor, visible);
        }
    }

    private class AnnotationScanner extends AnnotationVisitor {
        private final String methodName;
        private final int index;
        private final String descriptor;
        private final boolean visible;

        public AnnotationScanner(String methodName, int index, String descriptor, boolean visible) {
            super(Opcodes.ASM9);
            this.methodName = methodName;
            this.index = index;
            this.descriptor = descriptor;
            this.visible = visible;
        }

        @Override
        public void visit(String name, Object value) {
            add(new ParameterAnnotation(reader.getClassName(), methodName, index, descriptor, visible, name, value));
        }
    }
}
