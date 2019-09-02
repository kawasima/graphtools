package net.unit8.graphtool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

class ExportTest {
    @Test
    void test() throws JsonProcessingException {
        Graph graph = TinkerGraph.open();
        final Vertex vertex1 = graph.addVertex("Person");
        vertex1.property("name", "kawasima");
        vertex1.property("gender", "M");

        final Vertex vertex2 = graph.addVertex("Person");
        vertex2.property("name", "Elizabeth");
        vertex2.property("gender", "F");

        final Edge edge1 = vertex1.addEdge("friend", vertex2, "since", LocalDate.of(2010, 1, 1));

        final List<Person> persons = List.of(
                new Person.Builder()
                        .id(vertex1.id())
                        .name(Objects.toString(vertex1.property("name").value(), ""))
                        .gender(Objects.toString(vertex1.property("gender").value(), ""))
                        .build(),
                new Person.Builder()
                        .id(vertex2.id())
                        .name(Objects.toString(vertex2.property("name").value(), ""))
                        .gender(Objects.toString(vertex2.property("gender").value(), ""))
                        .build()
                );

        final List<Friend> friends = List.of(
                new Friend.Builder().build()
        );
        EntityMetadataProvider metadataProvider = new EntityMetadataProvider();
        CsvSchemaProvider schemaProvider = new CsvSchemaProvider(metadataProvider);
        final CsvSchema schema = schemaProvider.get(Person.class)
                .withoutHeader();

        CsvMapper mapper = new CsvMapper();
        final ObjectWriter writer = mapper.writer(schema);
        StringWriter sw = new StringWriter();
        persons.forEach(p -> {
            try {
                writer.writeValue(sw, p);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        System.out.println(sw);
    }
}
