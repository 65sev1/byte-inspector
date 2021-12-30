package cbr.access;

public class Method extends Access {
    protected int access;
    protected String name;
    protected String desc;
    protected String signature;

    public Method(int access, String name, String desc, String signature) {
        super(name, desc);
        this.access = access;
        this.name = name;
        this.desc = desc;
        this.signature = signature;
    }

    public int getAccess() {
        return access;
    }

    public String getSignature() {
        return signature;
    }

    public boolean isDefault() {
        return access == 0;
    }

    public boolean isPublic() {
        return access == 1;
    }

    public boolean isPrivate() {
        return access == 2;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", access=" + access +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
