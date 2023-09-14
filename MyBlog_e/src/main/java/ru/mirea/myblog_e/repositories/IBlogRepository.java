package ru.mirea.myblog_e.repositories;

import ru.mirea.myblog_e.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.myblog_e.models.Tag;
import ru.mirea.myblog_e.models.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Integer> {


//    List<Blog> findBlogByOrderByRating();
//
//    List<Blog> findBlogByOrderByViews();

    List<Blog> findBlogByTag(Integer tagId);

//    List<Blog> findBlogByTagsIsInOrderByRating(Collection<Set<Tag>> tags);
//
//    List<Blog> findBlogByTagsIsInOrderByViews(Collection<Set<Tag>> tags);

    List<Blog> findBlogsByCreator(User creator);

    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCase(String title, String summary, String text);

//    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseOrderByRating(String title, String summary, String text);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseOrderByViews(String title, String summary, String text);

    List<Blog> findAllByTitleOrSummaryOrTextContainingIgnoreCaseAndTag(String title, String summary, String text, Integer tagId);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingAndTagsContainingOrderByRating(String title, String summary, String text, Set<Tag> tags);
//
//    List<Blog> findAllByTitleOrSummaryOrTextContainingAndTagsContainingOrderByViews(String title, String summary, String text, Set<Tag> tags);


    Blog findById(Long id);

    @Transactional
    void deleteById(Long id);
}
