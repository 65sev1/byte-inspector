package cbr.object;

public abstract class Access {
    protected final String name;
    protected final String desc;

    public Access(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
