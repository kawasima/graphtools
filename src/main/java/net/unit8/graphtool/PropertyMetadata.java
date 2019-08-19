package net.unit8.graphtool;

import java.lang.reflect.Method;

public class PropertyMetadata {
    private String name;
    private Class<?> type;
    private Method getter;
    private Method reader;

    public PropertyMetadata(String name, Class<?> type, Method getter, Method reader) {
        this.name = name;
        this.type = type;
        this.getter = getter;
        this.reader = reader;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getReader() {
        return reader;
    }
}
