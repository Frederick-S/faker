package pikachu.faker;

import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Faker<T> {
    private Class<T> entityClass;

    private List<FieldValueGenerator> fieldValueGenerators;

    public Faker(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.fieldValueGenerators = new ArrayList<>();
    }

    public Faker<T> field(@NonNull String fieldName, @NonNull Supplier<Object> fieldValueGenerator) {
        this.fieldValueGenerators.add(new FieldValueGenerator(fieldName, fieldValueGenerator));

        return this;
    }

    public T generate() {
        T object;
        Field[] fields = this.getFields(this.entityClass);

        try {
            Constructor constructor = this.entityClass.getConstructor(new Class<?>[]{});
            object = (T) constructor.newInstance(new Object[]{});

            Arrays.stream(fields)
                    .forEach(field -> {
                        FieldValueGenerator fieldValueGenerator = this.findFieldValueGeneratorByFieldName(field.getName());

                        if (fieldValueGenerator != null) {
                            try {
                                field.setAccessible(true);
                                field.set(object, fieldValueGenerator.getGenerator().get());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return object;
    }

    private Field[] getFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Class superClass = clazz.getSuperclass();

        while (superClass != null) {
            fields = Stream.concat(Arrays.stream(fields), Arrays.stream(superClass.getDeclaredFields()))
                    .toArray(Field[]::new);

            superClass = superClass.getSuperclass();
        }

        return fields;
    }

    private FieldValueGenerator findFieldValueGeneratorByFieldName(@NonNull String fieldName) {
        return this.fieldValueGenerators.stream()
                .filter(fieldValueGenerator -> fieldValueGenerator.getFieldName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }
}
