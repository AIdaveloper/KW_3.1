package com.mirea.sweetshops.services;

import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirea.sweetshops.models.Blog;
import com.mirea.sweetshops.repositories.IBlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private IBlogRepository ibr;
    private IUserRepository iur;
    private TagService ts;
    private UserService us;

    @Autowired
    public BlogService(IBlogRepository ipr) {
        this.ibr = ipr;
    }


    public List<Blog> getFilteredBlogs(String search, Long tagId, Integer orderBy) throws Exception {
        if (search.isEmpty()){
            System.out.println(tagId);
            if (tagId == -1){
                return ibr.findAll();
            }
            else {
//                if (ts.getById(tagId) != null){
                    return ibr.findBlogByTag(tagId);
//                }else {throw new Exception("Wrong order type");}
            }
        }else {
            if (tagId == -1){
                return ibr.findAllByTitleOrSummaryOrTextContainingIgnoreCase(search, search, search);
            }
            else {
//                if (ts.getById(tagId) != null){
                    return ibr.findAllByTitleOrSummaryOrTextContainingIgnoreCaseAndTag(search, search, search, tagId);
//                }else {throw new Exception("Wrong order type");}
            }
        }
    }

    public Blog getBlogById(Long id) {
        return ibr.findById(id);
    }

    public List<Blog> getBlogByCreatorId(Long creatorId) {
        return ibr.findBlogsByCreator(creatorId);
    }

    public User getCreator(Long id){
        return us.findUserById(id);

    }

    public List<Blog> getAll(){ return ibr.findAll();}

    public void saveBlog(Blog blog){ibr.save(blog);}

    public void deleteBlogById(Long id){ibr.deleteById(id);}

}
