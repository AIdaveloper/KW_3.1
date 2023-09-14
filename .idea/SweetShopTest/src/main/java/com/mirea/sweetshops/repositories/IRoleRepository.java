package com.mirea.sweetshops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mirea.sweetshops.models.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
