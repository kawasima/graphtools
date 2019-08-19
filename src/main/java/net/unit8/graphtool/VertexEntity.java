package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "~label")
public abstract class VertexEntity {
    @JsonProperty("~id")
    protected Object id;

    public Object getId() {
        return id;
    }

    @Override
    public boolean equals(Object another) {
        return Optional.ofNullable(another)
                .filter(o -> o.getClass().equals(this.getClass()))
                .map(o -> this.getClass().cast(o))
                .filter(o -> o.getId().equals(this.getId()))
                .isPresent();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
