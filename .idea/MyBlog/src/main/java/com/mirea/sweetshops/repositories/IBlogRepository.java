package com.mirea.sweetshops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mirea.sweetshops.models.Blog;
import com.mirea.sweetshops.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findBlogByTag(Long tagId);

    List<Blog> findBlogsByCreator(Long creator);

    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCase(String title, String summary, String text);

//    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseOrderByRating(String title, String summary, String text);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseOrderByViews(String title, String summary, String text);

    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseAndTag(String title, String summary, String text, Long tagId);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingAndTagsContainingOrderByRating(String title, String summary, String text, Set<Tag> tags);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingAndTagsContainingOrderByViews(String title, String summary, String text, Set<Tag> tags);


    Blog findById(Long id);

    @Transactional
    void deleteById(Long id);
}
