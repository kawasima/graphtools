package net.unit8.graphtool;

import org.apache.tinkerpop.gremlin.structure.Edge;

import java.util.Set;

public class EdgeMetadata extends GraphElementMetadata {
    private static final Set<String> EXCLUDES_PROPERTY_NAMES = Set.of("id", "class", "from", "to");

    public EdgeMetadata(Class<? extends EdgeEntity> entityClass) {
        super(entityClass);
    }

    protected Set<String> getExcludesPropertyNames() {
        return EXCLUDES_PROPERTY_NAMES;
    }
}
