package com.p5.adoption;

import com.p5.adoption.model.AnimalDTO;
import com.p5.adoption.model.AnimalShelterDTO;
import com.p5.adoption.model.adapters.AnimalShelterAdapter;
import com.p5.adoption.repository.shelter.AnimalShelter;
import com.p5.adoption.repository.shelter.AnimalShelterRepository;
import com.p5.adoption.service.AnimalShelterService;
import com.p5.adoption.service.exceptions.ShelterAddressException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

// ExtendWith is used for variant 2 of declaring mocks
@ExtendWith(MockitoExtension.class)
public class TestAnimalShelterRepository {

    private final AnimalShelterDTO baseAnimal = new AnimalShelterDTO()
            .setId(1)
            .setName("TestName")
            .setAddress("TestBrasovAddress")
            .setAnimals(Collections.singletonList(new AnimalDTO()
                    .setName("TestName")
                    .setId(3)
                    .setPhotoUrl("https://anurl.com")));

    private final AnimalShelter baseShelter = AnimalShelterAdapter.fromDto(baseAnimal);

    //    Variant 1: mocking by hand and using init
    //    private AnimalShelterRepository shelterRepositoryMock;
    //    private AnimalShelterService animalShelterService;
    //
    //    @BeforeEach
    //    void init()
    //    {
    //        shelterRepositoryMock = Mockito.mock(AnimalShelterRepository.class);
    //        animalShelterService = new AnimalShelterService(shelterRepositoryMock);
    //    }


    //    Variant 2
    @Mock
    private AnimalShelterRepository shelterRepositoryMock;
    @InjectMocks
    private AnimalShelterService animalShelterService;


    @Test
    public void testAnimalShelterUpdate() {

        Mockito.when((shelterRepositoryMock.findById(Mockito.anyInt()))).thenReturn(Optional.of(baseShelter));
        Mockito.when((shelterRepositoryMock.save(Mockito.any(AnimalShelter.class)))).thenReturn(baseShelter);

        AnimalShelterDTO response = animalShelterService.updateShelter(baseAnimal);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getAddress()).isNotBlank();
        Assertions.assertThat(response.getId()).isEqualTo(baseShelter.getId());
    }


    @Test
    public void testInvalidAddressThrowsError() {

        baseAnimal.setAddress("TestAddress");


        ShelterAddressException exception = org.junit.jupiter.api.Assertions.assertThrows(
                ShelterAddressException.class,
                () -> animalShelterService.updateShelter(baseAnimal));
    }
}
