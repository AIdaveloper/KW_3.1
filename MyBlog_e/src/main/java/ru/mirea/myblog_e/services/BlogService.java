package ru.mirea.myblog_e.services;

import ch.qos.logback.core.joran.conditional.IfAction;
import ru.mirea.myblog_e.models.Blog;
import ru.mirea.myblog_e.models.Tag;
//import ru.mirea.myblog_e.services.TagService;
import ru.mirea.myblog_e.repositories.IBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogService {
    private IBlogRepository ibr;
    private TagService ts;
    private UserService us;

    @Autowired
    public BlogService(IBlogRepository ipr) {
        this.ibr = ipr;
    }


    public List<Blog> getFilteredBlogs(String search, Integer tagId, Integer orderBy) throws Exception {
        if (search.isEmpty()){
            if (tagId == -1){
                return ibr.findAll();
            }
            else {
                if (ts.getByTagId(tagId) != null){
                    return ibr.findBlogByTag(tagId);
                }else {throw new Exception("Wrong order type");}
            }
        }else {
            if (tagId == -1){
                return ibr.findAllByTitleOrSummaryOrTextContainingIgnoreCase(search, search, search);
            }
            else {
                if (ts.getByTagId(tagId) != null){
                    return ibr.findAllByTitleOrSummaryOrTextContainingIgnoreCaseAndTag(search, search, search, tagId);
                }else {throw new Exception("Wrong order type");}
            }
        }
    }

    public Blog getBlogById(Long id) {
        return ibr.findById(id);
    }

    public List<Blog> getBlogByCreatorId(Long creatorId) {
        return ibr.findBlogsByCreator(us.findUserById(creatorId));
    }

    public List<Blog> getAll(){ return ibr.findAll();}

    public void saveBlog(Blog blog){ibr.save(blog);}

    public void deleteBlogById(Long id){ibr.deleteById(id);}

}
