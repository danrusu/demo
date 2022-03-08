package utils;

import lombok.Data;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Data
public class GraphQLBody {
    private final String query;
    private final Map<String, ?> variables;

    @SneakyThrows
    public GraphQLBody(Path query, Map<String, ?> variables) {
        this.query = Files.readString(query);
        this.variables = variables;
    }

    public GraphQLBody(Path query) {
        this(query, Map.of());
    }
}