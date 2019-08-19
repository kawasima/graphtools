package net.unit8.graphtool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class ExportTest {
    @Test
    void test() throws JsonProcessingException {
        Graph graph = TinkerGraph.open();
        final org.apache.tinkerpop.gremlin.structure.Vertex vertex = graph.addVertex("Person");
        vertex.property("name", "kawasima");
        vertex.property("gender", "M");

        final PersonVertex person = new PersonVertex.Builder()
                .id(vertex.id())
                .name(Objects.toString(vertex.property("name").value(), ""))
                .gender(Objects.toString(vertex.property("gender").value(), ""))
                .build();
        EntityMetadataProvider metadataProvider = new EntityMetadataProvider();
        CsvSchemaProvider schemaProvider = new CsvSchemaProvider(metadataProvider);
        final CsvSchema schema = schemaProvider.get(person.getClass())
                .withHeader();

        CsvMapper mapper = new CsvMapper();
        System.out.println(mapper.writer(schema).writeValueAsString(person));
    }
}
