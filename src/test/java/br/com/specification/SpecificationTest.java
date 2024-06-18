package br.com.specification;

import br.com.specification.common.SearchCriteria;
import br.com.specification.common.SearchOperation;
import br.com.specification.common.SpecificationBuilder;
import br.com.specification.domain.UserEntity;
import br.com.specification.domain.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class SpecificationTest {

    @Autowired
    private UserRepository userRepository;

    private static UserEntity user1;
    private static UserEntity user2;
    private static UserEntity user3;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();

        user1 = new UserEntity(faker.name().fullName(), faker.internet().emailAddress(), 10, faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        user2 = new UserEntity(faker.name().fullName(), faker.internet().emailAddress(), 20, faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        user3 = new UserEntity(faker.name().fullName(), faker.internet().emailAddress(), 30, faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        userRepository.saveAll(List.of(user1, user2, user3));
    }

    @Test
    @DisplayName("Given users when search by age greater than then get users")
    void givenUsers_whenSearchByAgeGreaterThan_thenGetUsers() {
        // given
        int age = user3.getAge() - 1;

        // when
        SearchCriteria ageGreaterThan = new SearchCriteria("age", SearchOperation.GREATER_THAN, age);
        Specification spec = new SpecificationBuilder().build(List.of(ageGreaterThan));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(1);
        assertThat(users).extracting(UserEntity::getAge).allMatch(a -> a > age);
    }

    @Test
    @DisplayName("Given users when search by age less than then get users")
    void givenUsers_whenSearchByAgeLessThan_thenGetUsers() {
        // given
        int age = user1.getAge() + 1;

        // when
        SearchCriteria ageLessThan = new SearchCriteria("age", SearchOperation.LESS_THAN, age);
        Specification spec = new SpecificationBuilder().build(List.of(ageLessThan));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(1);
        assertThat(users).extracting(UserEntity::getAge).allMatch(a -> a < age);
    }

    @Test
    @DisplayName("Given users when search by age equal then get users")
    void givenUsers_whenSearchByAgeEqual_thenGetUsers() {
        // given
        int age = user2.getAge();

        // when
        SearchCriteria ageEqual = new SearchCriteria("age", SearchOperation.EQUALS, age);
        Specification spec = new SpecificationBuilder().build(List.of(ageEqual));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(1);
        assertThat(users).extracting(UserEntity::getAge).allMatch(a -> a == age);
    }

    @Test
    @DisplayName("Given users when search by age not equal then get users")
    void givenUsers_whenSearchByAgeNotEqual_thenGetUsers() {
        // given
        int age = user2.getAge();

        // when
        SearchCriteria ageNotEqual = new SearchCriteria("age", SearchOperation.NEGATE, age);
        Specification spec = new SpecificationBuilder().build(List.of(ageNotEqual));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(2);
        assertThat(users).extracting(UserEntity::getAge).allMatch(a -> a != age);
    }

    @Test
    @DisplayName("Given users when search by name contains then get users")
    void givenUsers_whenSearchByContains_thenGetUsers() {
        // given
        String name = user1.getName().substring(1, 5);

        // when
        SearchCriteria nameLike = new SearchCriteria("name", SearchOperation.CONTAINS, name);
        Specification spec = new SpecificationBuilder().build(List.of(nameLike));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(1);
        assertThat(users).extracting(UserEntity::getName).allMatch(n -> n.contains(name));
    }

    @Test
    @DisplayName("Given users when search by name starts with then get users")
    void givenUsers_whenSearchByStartsWith_thenGetUsers() {
        // given
        String name = user1.getName().substring(0, 5);

        // when
        SearchCriteria nameStartsWith = new SearchCriteria("name", SearchOperation.STARTS_WITH, name);
        Specification spec = new SpecificationBuilder().build(List.of(nameStartsWith));

        List<UserEntity> users = userRepository.findAll(spec);

        // then
        assertThat(users).hasSize(1);
        assertThat(users).extracting(UserEntity::getName).allMatch(n -> n.startsWith(name));
    }

}