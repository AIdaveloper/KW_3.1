package ru.mirea.myblog_e.services;

import ru.mirea.myblog_e.models.Tag;
import ru.mirea.myblog_e.repositories.ITagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private ITagRepository itr;

    @Autowired
    public TagService(ITagRepository itr){this.itr = itr;}

    public Tag getByTagId(int typeId){return itr.findByTagId(typeId);}

    public List<Tag> getAllTags() {return itr.findAll();}


    public void saveTag(Tag type) {
        itr.save(type);
    }


    public void deleteByTagId(int typeId){itr.deleteByTagId(typeId);}
}
