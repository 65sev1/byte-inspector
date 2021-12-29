package cbr;

import cbr.access.Access;
import cbr.access.Field;
import cbr.access.Invoke;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.ASM9;

public class SecurityMethodVisitor extends MethodVisitor {
    private final List<Access> insns = new ArrayList<>();
    private int line = 0;

    private SecurityMethodVisitor() {
        super(ASM9);
    }

    public static List<Access> visit(MethodNode method) {
        return new SecurityMethodVisitor().accept(method);
    }

    public List<Access> accept(MethodNode method) {
        method.accept(this);
        return insns;
    }

    /* Visits a method instruction. A method instruction is an instruction that invokes a method. */
    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        insns.add(new Invoke(line, opcode, owner, name, descriptor, isInterface));
    }

    /* Visits a field instruction. A field instruction is an instruction that loads or stores the value of a field of an object. */
    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        super.visitFieldInsn(opcode, owner, name, descriptor);
        insns.add(new Field(line, opcode, owner, name, descriptor));
    }

    /* Visits a type instruction. A type instruction is an instruction that takes the internal name of a class as parameter. */
    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
    }

    /* Visits a LDC instruction. Note that new constant types may be added in future versions of the Java Virtual Machine. To easily detect new constant types, implementations of this method should check for unexpected constant types, like this */
    @Override
    public void visitLdcInsn(Object value) {
        super.visitLdcInsn(value);
    }

    /* Visits a zero operand instruction. */
    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
        this.line = line;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        return super.visitParameterAnnotation(parameter, descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        return super.visitAnnotationDefault();
    }
}
