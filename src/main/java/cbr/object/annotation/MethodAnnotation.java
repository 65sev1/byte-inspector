package cbr.object.annotation;

import cbr.object.ScanObject;

public class MethodAnnotation implements ScanObject {
    private final String className;
    private final String methodName;
    private final String descriptor;
    private final boolean visible;
    private final String name;
    private final Object value;

    public MethodAnnotation(String className, String methodName, String descriptor, boolean visible, String name, Object value) {
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
        this.visible = visible;
        this.name = name;
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
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
        return "MethodAnnotation{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", visible=" + visible +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
