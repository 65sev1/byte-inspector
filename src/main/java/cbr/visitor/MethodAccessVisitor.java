package cbr.visitor;

import org.objectweb.asm.*;

public class MethodAccessVisitor extends MethodVisitor {
    MethodAccessVisitor() {
        super(Opcodes.ASM9);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("method annotation: desc=" + desc + " visible=" + visible);
        return new AnnotationAccessVisitor();
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        System.out.println("method param annotation: parameter=" + parameter + " descriptor=" + descriptor + " visible=" + visible);
        return new AnnotationAccessVisitor();
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        System.out.println("method param: name=" + name + " descriptor=" + descriptor + " signature=" + signature + " start=" + start + " end=" + end + " index=" + index);
        super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return super.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        System.out.println("method field: opcode=" + opcode + " owner=" + owner + " name=" + name + " descriptor=" + descriptor);
        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        System.out.println("method instruction: opcode=" + opcode + " owner=" + owner + " name=" + name + " descriptor=" + descriptor);
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}
