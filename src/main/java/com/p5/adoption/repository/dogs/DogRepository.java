package com.p5.adoption.repository.dogs;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog,Integer> {

    Dog findDogByUrl(String url);

}
