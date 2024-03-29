package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public abstract class VertexEntity implements GraphEntity {
    @JsonProperty("~id")
    protected Object id;

    @Override
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
