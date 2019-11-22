package by.academy.it.global.services;

import by.academy.it.global.entity.Person;

public interface IPersonService {

    Person create(Person person);
    Person createWithException(Person person);

    void delete(Person person);
}
