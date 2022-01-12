package cbr.object.annotation;

public class ClassAnnotation {
    private final String className;
    private final String descriptor;
    private final boolean visible;
    private final String name;
    private final Object value;

    public ClassAnnotation(String className, String descriptor, boolean visible, String name, Object value) {
        this.className = className;
        this.descriptor = descriptor;
        this.visible = visible;
        this.name = name;
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ClassAnnotation{" +
                "className='" + className + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", visible=" + visible +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
