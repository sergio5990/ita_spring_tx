package by.academy.it.global.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.academy.it.global.entity.Person;

@Service
@Transactional
public class PersonService implements IPersonService {
    @PersistenceContext(unitName = "unit-emfA")
    private EntityManager emA;
    @PersistenceContext(unitName = "unit-emfB")
    private EntityManager emB;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Person create(Person personA) {
        if (personA != null) {
            Person personB = new Person();
            personB.setName(personA.getName());
            personB.setSurname(personA.getSurname());
            personB.setAge(personA.getAge());
            emA.persist(personA);
            emB.persist(personB);
        }
        return personA;
    }

    @Override
    public Person createWithException(Person personA) {
        if (personA != null) {
            Person personB = new Person();
            personB.setName(personA.getName());
            personB.setSurname(personA.getSurname());
            personB.setAge(personA.getAge());
            emA.persist(personA);
            throw new JpaSystemException(new PersistenceException("Could not persist personA"));
        }
        return personA;
    }

    public void delete(Person person) {
        if (person != null) {
            emA.remove(person);
            emB.remove(person);
        }
    }
}
