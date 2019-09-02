package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.PROPERTY,
        property = "~label")
@JsonTypeIdResolver(CustomTypeIdResolver.class)
public interface GraphEntity {
    Object getId();
}
