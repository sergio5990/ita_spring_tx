package by.academy.it;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.academy.it.global.entity.Person;
import by.academy.it.global.services.IPersonService;

/**
 * Class GlobalTransactionTest
 *
 * Created by yslabko on 11/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:global-context.xml")
public class GlobalTransactionTest {
    @PersistenceContext(unitName = "unit-emfA")
    private EntityManager emA;
    @PersistenceContext(unitName = "unit-emfB")
    private EntityManager emB;
    @Autowired
    private IPersonService personService;

    @Test
    public void saveTest() {
        Person person = new Person();
        person.setName("Yuli");
        person.setSurname("Slabko");
        person.setAge(43);
        person = personService.create(person);
        emA.clear();
        emB.clear();
        Person personA = emA.find(Person.class, person.getId());
        Person personB = emB.find(Person.class, person.getId());
        System.out.println("Person saved successfully: " + personA);
        System.out.println("Person saved successfully: " + personB);
        Assert.assertEquals(person, personA);
        Assert.assertEquals(person, personB);
    }

    @Test
    public void saveWithExceptionTest() {
        Person person = new Person();
        person.setName("Yuli");
        person.setSurname("Slabko");
        try {
            person = personService.createWithException(person);
        } catch (Exception e) {

        }
        emA.clear();
        Person personA = emA.find(Person.class, person.getId());
        System.out.println("Person A was not saved successfully: " + person);
        Assert.assertNull(personA);
    }
}