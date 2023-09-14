package ru.mirea.myblog_e.repositories;

import ru.mirea.myblog_e.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Integer> {

    Tag findByTagId(int typeId);

    @Transactional
    void deleteByTagId(int typeId);

}
