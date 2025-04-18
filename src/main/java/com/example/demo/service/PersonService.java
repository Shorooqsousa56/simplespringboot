package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }
    public int addPerson(person person) {
        return personDao.insertPerson(person);

    }
    public List<person> getAllPeople() {
        return personDao.selectAllPeople();
    }
    public Optional<person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }
    public int updatePerson(UUID id, person newperson) {
        return personDao.updatePerson(id,newperson);
    }
    public int deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }

}
