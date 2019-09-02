package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GraphElementMetadata {
    private final String label;
    private final Map<String, PropertyMetadata> propertyMetaMap;

    public GraphElementMetadata(Class<? extends GraphEntity> entityClass) {
        final GraphLabel graphLabelAnnotation = entityClass.getAnnotation(GraphLabel.class);
        if (graphLabelAnnotation == null) {
            throw new IllegalArgumentException(entityClass + " is not GraphEntity");
        }
        label = graphLabelAnnotation.value();
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(entityClass);
            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            propertyMetaMap = Arrays.stream(propertyDescriptors)
                    .filter(pd -> !getExcludesPropertyNames().contains(pd.getName()))
                    .map(pd -> new PropertyMetadata(pd.getName(), pd.getPropertyType(), pd.getReadMethod(), pd.getWriteMethod()))
                    .collect(Collectors.toMap(PropertyMetadata::getName, m -> m));
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

    }

    protected abstract Set<String> getExcludesPropertyNames();

    public Stream<PropertyMetadata> propertyMetadataStream() {
        return propertyMetaMap.values().stream();
    }

    public String getLabel() {
        return label;
    }
}
