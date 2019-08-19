package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Vertex("person")
@JsonTypeName("person")
public class PersonVertex extends VertexEntity {
    @JsonProperty("name:String")
    private String name;
    @JsonProperty("gender:String")
    private String gender;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public static class Builder {
        private final PersonVertex person;

        public Builder() {
            person = new PersonVertex();
        }

        public Builder id(Object id) {
            person.id = id;
            return this;
        }

        public Builder name(String name) {
            person.name = name;
            return this;
        }

        public Builder gender(String gender) {
            person.gender = gender;
            return this;
        }

        public PersonVertex build() {
            return person;
        }
    }
}
