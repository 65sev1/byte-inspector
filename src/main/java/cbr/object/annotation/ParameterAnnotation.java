package cbr.object.annotation;

import cbr.object.ScanObject;

public class ParameterAnnotation implements ScanObject {
    private final String className;
    private final String methodName;
    private final int index;
    private final String descriptor;
    private final boolean visible;
    private final String name;
    private final Object value;

    public ParameterAnnotation(String className, String methodName, int index, String descriptor, boolean visible, String name, Object value) {
        this.className = className;
        this.methodName = methodName;
        this.index = index;
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

    public int getIndex() {
        return index;
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
        return "ParameterAnnotation{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", index=" + index +
                ", descriptor='" + descriptor + '\'' +
                ", visible=" + visible +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
