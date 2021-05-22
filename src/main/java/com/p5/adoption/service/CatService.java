package com.p5.adoption.service;

import com.p5.adoption.model.CatDTO;
import com.p5.adoption.model.ListDTO;
import com.p5.adoption.model.adapters.CatAdapter;
import com.p5.adoption.repository.cats.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {


    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(CatDTO catDTO) {
        if (catDTO.getName() == null && catDTO.getPhotoUrl() == null) {

            throw new RuntimeException("Cat must have a name and a photo!");
        }

        catRepository.save(CatAdapter.fromDto(catDTO));
    }

    public ListDTO<CatDTO> findAll()
    {
        List<CatDTO> cats = CatAdapter.toListDTO(catRepository.findAll());
        Long totalCount = catRepository.count();
        return  new ListDTO<CatDTO>(Math.toIntExact(totalCount),cats);
    }

    public CatDTO findCat(String name)
    {
        if(name == null || name.equals(""))
        {
            throw new RuntimeException("Must specify name!");
        }

        return CatAdapter.toDto(catRepository.findCatByName(name));
    }
}
