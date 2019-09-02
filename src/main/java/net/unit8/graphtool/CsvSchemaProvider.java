package net.unit8.graphtool;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvSchemaProvider {
    private EntityMetadataProvider entityMetadataProvider;

    CsvSchemaProvider(EntityMetadataProvider entityMetadataProvider) {
        this.entityMetadataProvider = entityMetadataProvider;
    }

    public CsvSchema get(Class<? extends VertexEntity> entityClass) {
        final GraphElementMetadata graphElementMetadata = entityMetadataProvider.get(entityClass);
        CsvSchema.Builder builder = CsvSchema.builder();
        builder.addColumn("~id");
        builder.addColumn("~label", CsvSchema.ColumnType.STRING);
        graphElementMetadata.propertyMetadataStream().forEach(meta -> {
            builder.addColumn(meta.getName() + toTypeName(meta.getType()),
                    CsvSchema.ColumnType.STRING);
        });
        return builder.build();
    }

    private String toTypeName(Class<?> type) {
        if (String.class.isAssignableFrom(type)) {
            return ":String";
        } else if (Integer.class.isAssignableFrom(type)) {
            return ":Int";
        } else if (Boolean.class.isAssignableFrom(type)) {
            return ":Boolean";
        } else if (Double.class.isAssignableFrom(type)) {
            return ":Double";
        } else {
            return "";
        }
    }
}
