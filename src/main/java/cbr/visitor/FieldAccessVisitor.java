package cbr.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class FieldAccessVisitor extends FieldVisitor {
    public FieldAccessVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("desc=" + desc + " visible=" + visible);
        return super.visitAnnotation(desc, visible);
    }


}
