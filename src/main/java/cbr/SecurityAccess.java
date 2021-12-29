package cbr;

import cbr.access.Access;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.ASM9;

public class SecurityAccess {

    public static List<Access> inspect(String name) {
        final List<Access> insns = new ArrayList<>();

        new MethodAnnotationScanner();

        /*try (var in = new FileInputStream(name)) {
            var node = new ClassNode();
            var reader = new ClassReader(in);
            reader.accept(node, 0);

            // обход методов
            /*for (MethodNode method : node.methods) {
                insns.add(new Method(method.access, method.name, method.desc, method.signature));
                insns.addAll(SecurityMethodVisitor.visit(method));

                // обход аннотаций
                /*if (node.visibleAnnotations != null) {
                    for (AnnotationNode annotation : node.visibleAnnotations) {
                        new SecurityAnnotationVisitor().accept(method.visitAnnotation(annotation.desc, true));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return insns;
    }

    public static class SecurityAnnotationVisitor extends AnnotationVisitor {
        private final List<Access> insns = new ArrayList<>();

        public SecurityAnnotationVisitor() {
            super(ASM9);
        }
    }

    public static class FieldAnnotationScanner extends FieldVisitor {
        public FieldAnnotationScanner() {
            super(Opcodes.ASM5);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            System.out.println("visitAnnotation: desc="+desc+" visible="+visible);
            return super.visitAnnotation(desc, visible);
        }
    }

    public static class MethodAnnotationScanner extends MethodVisitor {
        public MethodAnnotationScanner() {
            super(Opcodes.ASM5);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            System.out.println("visitAnnotation: desc="+desc+" visible="+visible);
            return super.visitAnnotation(desc, visible);
        }
    }
}
