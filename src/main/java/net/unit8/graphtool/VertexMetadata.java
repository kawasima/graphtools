package net.unit8.graphtool;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VertexMetadata {
    private String label;
    private Map<String, PropertyMetadata> propertyMetaMap;
    private static final Set<String> EXCLUDES_PROPERTY_NAMES = Set.of("id", "class");

    public VertexMetadata(Class<? extends VertexEntity> entityClass) {
        final Vertex vertexAnnotation = entityClass.getAnnotation(Vertex.class);
        if (vertexAnnotation == null) {
            throw new IllegalArgumentException(entityClass + " is not VertexEntity");
        }
        label = vertexAnnotation.value();

        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(entityClass);
            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            propertyMetaMap = Arrays.stream(propertyDescriptors)
                    .filter(pd -> !EXCLUDES_PROPERTY_NAMES.contains(pd.getName()))
                    .map(pd -> new PropertyMetadata(pd.getName(), pd.getPropertyType(), pd.getReadMethod(), pd.getWriteMethod()))
                    .collect(Collectors.toMap(PropertyMetadata::getName, m -> m));
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLabel() {
        return label;
    }

    public Stream<PropertyMetadata> propertyMetadataStream() {
        return propertyMetaMap.values().stream();
    }
}
