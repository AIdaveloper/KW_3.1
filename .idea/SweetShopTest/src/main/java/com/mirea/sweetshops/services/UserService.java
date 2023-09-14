package com.mirea.sweetshops.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mirea.sweetshops.models.Role;
import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.repositories.IRoleRepository;
import com.mirea.sweetshops.repositories.IUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    IUserRepository iur;

    @PersistenceContext
    private EntityManager em;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public void create(String email, String username, String password) {
        System.out.println("create");

        User u = new User();
        System.out.println("1");
        u.setUsername(username);
        System.out.println("2");
        u.setPassword(bCryptPasswordEncoder.encode(password));
        System.out.println("3");
        u.setEmail(email);
        System.out.println("4");
        System.out.println("5");
        System.out.println(u);
        userRepository.save(u);
        System.out.println("6");
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) {
////        System.out.println("load");
////        User user = userRepository.findByUsername(username);
//        System.out.println("load : " + email);
//        User user = userRepository.findByEmail(email);
//        System.out.println("result");
//        System.out.println(user);
//
//        return user;
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
        throw new UsernameNotFoundException("User not found");
    }

    return user;
}

    public User findUserById(Long userId) {
//        Optional<User> userFromDb = userRepository.findById(userId);
//        return userFromDb.orElse(new User());
        return userRepository.getById(userId);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user, Long role) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(role, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public void deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }
    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
