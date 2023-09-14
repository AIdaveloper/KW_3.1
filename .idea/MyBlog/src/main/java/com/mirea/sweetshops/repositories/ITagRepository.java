package com.mirea.sweetshops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mirea.sweetshops.models.Tag;

import javax.transaction.Transactional;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Integer> {

//    Tag findByTagId(int typeId);
    Tag getById(Long id);

    @Transactional
    void deleteById(Long id);

}
