package ru.mirea.myblog_e.repositories;

import ru.mirea.myblog_e.models.Blog;
import ru.mirea.myblog_e.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    User findByBlogs(Set<Blog> blogs);

    User findByEmail(String email);
}
