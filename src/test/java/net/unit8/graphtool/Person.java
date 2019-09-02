package net.unit8.graphtool;

import com.fasterxml.jackson.annotation.JsonProperty;

@GraphLabel("person")
public class Person extends VertexEntity {
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
        private final Person person;

        public Builder() {
            person = new Person();
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

        public Person build() {
            return person;
        }
    }
}
