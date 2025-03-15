package com.example.demo.dao;

import com.example.demo.model.person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<person> DB =new ArrayList<>();

    @Override
    public int insertPerson(UUID id, person person) {
        DB.add(new person(id, person.getName()));
        return 1;
    }

    @Override
    public List<person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<person> Maybeperson = selectPersonById(id);
        if (Maybeperson.isEmpty()) {
            return 0;
        }
        DB.remove(Maybeperson.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, person update) {
        return selectPersonById(id).
                map(person->{
                    int indexOfPerson = DB.indexOf(person);
                    if(indexOfPerson>=0){
                        DB.set(indexOfPerson,new person(id, update.getName()));
                        return 1;
                    }
                    return 0;

                }).orElse(0);
    }


}
