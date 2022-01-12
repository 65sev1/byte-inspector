package cbr.object;

public class Invoke extends AccessInsns {
    private final boolean isInterface;

    public Invoke(int line, int opcode, String owner, String name, String desc, boolean isInterface) {
        super(line, opcode, owner, name, desc);
        this.isInterface = isInterface;
    }

    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public String toString() {
        return "Invoke{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", line=" + line +
                ", opcode=" + opcode +
                ", owner='" + owner + '\'' +
                '}';
    }
}
