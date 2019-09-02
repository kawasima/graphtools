package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class CustomTypeIdResolver extends TypeIdResolverBase {
    private EntityMetadataProvider entityMetadataProvider;

    public CustomTypeIdResolver() {
        entityMetadataProvider = new EntityMetadataProvider();
    }
    @Override
    public String idFromValue(Object value) {
        return _idFrom(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return _idFrom(value, suggestedType);
    }

    private String _idFrom(Object value, Class<?> type) {
        if (GraphEntity.class.isAssignableFrom(type)) {
            final GraphElementMetadata graphElementMetadata = entityMetadataProvider.get((Class<? extends GraphEntity>) type);
            return graphElementMetadata.getLabel();
        } else {
            return null;
        }
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
