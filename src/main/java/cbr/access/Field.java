package cbr.access;

public class Field extends AccessInsns {

    public Field(int line, int opcode, String owner, String name, String desc) {
        super(line, opcode, owner, name, desc);
    }

    public boolean isStatic() {
        return (opcode == OpCode.GETSTATIC.code) || (opcode == OpCode.PUTSTATIC.code);
    }

    public boolean isReadAccess() {
        return (opcode == OpCode.GETSTATIC.code) || (opcode == OpCode.GETFIELD.code);
    }

    public boolean isWriteAccess() {
        return (opcode == OpCode.PUTSTATIC.code) || (opcode == OpCode.PUTFIELD.code);
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", line=" + line +
                ", opcode=" + opcode +
                ", owner='" + owner + '\'' +
                '}';
    }
}
