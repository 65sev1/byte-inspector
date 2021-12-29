package cbr.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class AnnotationAccessVisitor extends AnnotationVisitor {
    AnnotationAccessVisitor() {
        super(Opcodes.ASM9);
    }

    @Override
    public void visit(String name, Object value) {
        System.out.println("annotation: name=" + name + " value=" + value);
        super.visit(name, value);
    }
}
