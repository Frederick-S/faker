# faker [![CircleCI](https://circleci.com/gh/Frederick-S/faker.svg?style=shield)](https://circleci.com/gh/Frederick-S/faker) [![codecov](https://codecov.io/gh/Frederick-S/faker/branch/master/graph/badge.svg)](https://codecov.io/gh/Frederick-S/faker) [![Maintainability](https://api.codeclimate.com/v1/badges/919bac46411c60085351/maintainability)](https://codeclimate.com/github/Frederick-S/faker/maintainability)

## Installation
```
<dependency>
    <groupId>com.github.frederick-s</groupId>
    <artifactId>faker</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Usage
```java
Person person = new Faker<>(Person.class)
        .field("name", () -> "Tom")
        .field("age", () -> 10)
        .generate();
```
