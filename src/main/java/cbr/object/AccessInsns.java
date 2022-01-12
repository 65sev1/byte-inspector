package cbr.object;

public abstract class AccessInsns extends Access {
    protected final int line;
    protected final int opcode;
    protected final String owner;

    public AccessInsns(int line, int opcode, String owner, String name, String desc) {
        super(name, desc);
        this.line = line;
        this.opcode = opcode;
        this.owner = owner;
    }

    public int getLine() {
        return line;
    }

    public int getOpcode() {
        return opcode;
    }

    public String getOwner() {
        return owner;
    }
}
