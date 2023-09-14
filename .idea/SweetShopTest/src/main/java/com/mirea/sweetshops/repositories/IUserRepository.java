package com.mirea.sweetshops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mirea.sweetshops.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User getById(Long id);

    User findByEmail(String email);
}
