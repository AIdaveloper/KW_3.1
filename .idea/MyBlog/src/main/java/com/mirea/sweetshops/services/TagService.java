package com.mirea.sweetshops.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirea.sweetshops.models.Tag;
import com.mirea.sweetshops.repositories.ITagRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private ITagRepository itr;

    @Autowired
    public TagService(ITagRepository itr){this.itr = itr;}

    public Tag getById(Long Id){return itr.getById(Id);}

    public List<Tag> getAllTags() {return itr.findAll();}


    public void saveTag(Tag type) {
        itr.save(type);
    }


    public void deleteById(Long typeId){itr.deleteById(typeId);}
}
