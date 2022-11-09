package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Events, String> {
    @Query("{name:'?0'}")
    Events findItemByName(String name);

//    @Query(value="{category:'?0'}", fields="{'name' : 1, 'event' : 1}")
//    List<Events> findAll(String category);

    public long count();
}

