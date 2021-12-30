package cbr;

import cbr.access.Access;
import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SecurityAccess {

    public static List<Access> inspect(String name) {
        try (var in = new FileInputStream(name)) {
            var reader = new ClassReader(in);
            var visitor = AccessClass.create();
            reader.accept(visitor, 0);
            return visitor.getVisitObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class AccessClass extends ClassVisitor {
        private final List<Access> visitObj = new ArrayList<>();

        private AccessClass() {
            super(Opcodes.ASM9);
        }

        public static AccessClass create() {
            return new AccessClass();
        }

        public List<Access> getVisitObj() {
            return visitObj;
        }

        /* Visits annotation of the class. */
        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println("class annotation: descriptor=" + descriptor + " visible=" + visible);
            return new AccessAnnotation();
        }

        /* Visits a field of the class. */
        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            System.out.println("class field: access=" + access + " name=" + name + " desc=" + desc + " signature=" + signature + " value=" + value);
            return new AccessField();
        }

        /* Visits a method of the class. This method must return a new CodeVisitor instance (or null) each time it is called, i.e., it should not return a previously returned visitor. */
        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            System.out.println("class method: access=" + access + " name=" + name + " desc=" + desc + " signature=" + signature);
            return new AccessMethod();
        }
    }

    public static class AccessAnnotation extends AnnotationVisitor {
        public String name;
        public Object value;

        public AccessAnnotation() {
            super(Opcodes.ASM9);
        }

        @Override
        public void visit(String name, Object value) {
            System.out.println("annotation: name=" + name + " value=" + value);
            this.name = name;
            this.value = value;
            super.visit(name, value);
        }
    }

    public static class AccessField extends FieldVisitor {
        public AccessField() {
            super(Opcodes.ASM5);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            System.out.println("field annotation: desc=" + desc + " visible=" + visible);
            return new AccessAnnotation();
        }
    }

    public static class AccessMethod extends MethodVisitor {
        public AccessMethod() {
            super(Opcodes.ASM9);
        }

        /* Visits an annotation of this method. */
        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            System.out.println("method annotation: desc=" + desc + " visible=" + visible);
            return new AccessAnnotation();
        }

        /* Visits an annotation of a parameter this method. */
        @Override
        public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
            System.out.println("method param annotation: parameter=" + parameter + " descriptor=" + descriptor + " visible=" + visible);
            return new AccessAnnotation();
        }

        /* Visits a local variable declaration. */
        @Override
        public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
            System.out.println("method param: name=" + name + " descriptor=" + descriptor + " signature=" + signature + " start=" + start + " end=" + end + " index=" + index);
            super.visitLocalVariable(name, descriptor, signature, start, end, index);
        }

        /* Visits an annotation on an instruction. This method must be called just after the annotated instruction. It can be called several times for the same instruction. */
        @Override
        public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
            return super.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
        }

        /* Visits a field instruction. A field instruction is an instruction that loads or stores the value of a field of an object. */
        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
            System.out.println("method field: opcode=" + opcode + " owner=" + owner + " name=" + name + " descriptor=" + descriptor);
            super.visitFieldInsn(opcode, owner, name, descriptor);
        }

        /* Visits a method instruction. A method instruction is an instruction that invokes a method. */
        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            System.out.println("method instruction: opcode=" + opcode + " owner=" + owner + " name=" + name + " descriptor=" + descriptor);
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
