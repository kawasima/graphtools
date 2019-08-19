package net.unit8.graphtool;

import java.util.HashMap;
import java.util.WeakHashMap;

public class EntityMetadataProvider {
    private WeakHashMap<Class<? extends VertexEntity>, VertexMetadata> metadataMap;

    EntityMetadataProvider() {
        metadataMap = new WeakHashMap<>();
    }

    VertexMetadata get(Class<? extends VertexEntity> clazz) {
        return metadataMap.computeIfAbsent(clazz, VertexMetadata::new);
    }
}
