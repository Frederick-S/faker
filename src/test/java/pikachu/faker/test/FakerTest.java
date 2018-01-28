package pikachu.faker.test;

import org.junit.Assert;
import org.junit.Test;
import pikachu.faker.Faker;

public class FakerTest {
    @Test
    public void shouldGeneratePersonWithNonNullProperties() {
        Person person = new Faker<>(Person.class)
                .field("name", () -> "Tom")
                .field("age", () -> 10)
                .generate();

        Assert.assertNotNull(person);
        Assert.assertNotNull(person.getName());
        Assert.assertNotNull(person.getAge());
    }

    @Test
    public void shouldGenerateBaseClassProperties() {
        Student student = new Faker<>(Student.class)
                .field("name", () -> "Tom")
                .field("age", () -> 10)
                .field("grade", () -> 100.0)
                .generate();

        Assert.assertNotNull(student);
        Assert.assertNotNull(student.getName());
        Assert.assertNotNull(student.getAge());
        Assert.assertNotNull(student.getGrade());
    }
}
