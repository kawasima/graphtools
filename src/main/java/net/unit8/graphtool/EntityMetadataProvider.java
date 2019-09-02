package net.unit8.graphtool;

import java.util.WeakHashMap;

public class EntityMetadataProvider {
    private WeakHashMap<Class<? extends GraphEntity>, GraphElementMetadata> metadataMap;

    EntityMetadataProvider() {
        metadataMap = new WeakHashMap<>();
    }

    GraphElementMetadata get(Class<? extends GraphEntity> clazz) {
        if (VertexEntity.class.isAssignableFrom(clazz)) {
            return metadataMap.computeIfAbsent(clazz, (key) -> new VertexMetadata((Class<VertexEntity>)clazz));
        } else if (EdgeEntity.class.isAssignableFrom(clazz)) {
            return metadataMap.computeIfAbsent(clazz, (key) -> new EdgeMetadata((Class<EdgeEntity>)clazz));
        } else {
            throw new IllegalArgumentException(clazz + " is not a GraphEntity class");
        }
    }
}
