package cbr.visitor;

import org.objectweb.asm.*;
import test.TestClass1;

public class Visitor extends ClassVisitor {
    Visitor() {
        super(Opcodes.ASM9);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("class annotation: descriptor=" + descriptor + " visible=" + visible);
        return new AnnotationAccessVisitor();
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("class field: access=" + access + " name=" + name + " desc=" + desc + " signature=" + signature + " value=" + value);
        return new FieldAccessVisitor();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("class method: access=" + access + " name=" + name + " desc=" + desc + " signature=" + signature);
        return new MethodAccessVisitor();
    }

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader(TestClass1.class.getCanonicalName());
        cr.accept(new Visitor(), 0);
    }
}
