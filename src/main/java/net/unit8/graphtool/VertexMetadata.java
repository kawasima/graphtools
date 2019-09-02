package net.unit8.graphtool;

import java.util.Set;

public class VertexMetadata extends GraphElementMetadata {
    private static final Set<String> EXCLUDES_PROPERTY_NAMES = Set.of("id", "class");

    public VertexMetadata(Class<? extends VertexEntity> entityClass) {
        super(entityClass);
    }

    protected Set<String> getExcludesPropertyNames() {
        return EXCLUDES_PROPERTY_NAMES;
    }
}
