package pikachu.faker;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Supplier;

@Data
@AllArgsConstructor
class FieldValueGenerator {
    private String fieldName;

    private Supplier<Object> generator;
}
