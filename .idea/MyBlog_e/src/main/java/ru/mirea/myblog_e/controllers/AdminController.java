package ru.mirea.myblog_e.controllers;

import ru.mirea.myblog_e.dto.UserDto;
import ru.mirea.myblog_e.models.Blog;
import ru.mirea.myblog_e.models.Tag;
import ru.mirea.myblog_e.models.User;
import ru.mirea.myblog_e.services.BlogService;
import ru.mirea.myblog_e.services.TagService;
import ru.mirea.myblog_e.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private UserService userService;

    private final BlogService blogService;

    private final TagService tagService;


    private Set<String> getUserRole(Authentication authentication) {
        if (authentication == null) {
            Set<String> roles = new HashSet<>();
            roles.add("GUEST");
            return roles;
        }
        else{
            Set<String> roles = new HashSet<>();
            ((User) userService.loadUserByUsername(authentication.getName())).getRoles().forEach(iter->{
                roles.add(iter.getName());
            });
            return roles;
        }
    }

    private User getUser(Authentication authentication) {
        if (authentication == null)
            return null;
        else
            return (User) userService.loadUserByUsername(authentication.getName());
    }

    @GetMapping("/admin")
    public String userList(Model model, Authentication authentication) {
        model.addAttribute("userName", getUser(authentication).getUsername());
        return "AdminController/admin";
    }



//    @GetMapping("/admin/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
//        return "admin";
//    }

//    @GetMapping("/admin/types")
//    public String types(Model model) {
//        model.addAttribute("types", tagService.getAllTypes());
//        return "AdminController/admin-types";
//    }
//
//    @PostMapping("/admin/types/create")
//    public String typesCreate(@RequestParam(name = "name") String name,
//                              @RequestParam(name = "typeId") int typeId) {
//        Tag newType = new Tag();
//        newType.setName(name);
//        newType.setTypeId(typeId);
//        tagService.saveType(newType);
//        return "redirect:/admin/types";
//    }
//
//    @PostMapping("/admin/types/delete")
//    public String typesDelete(@RequestParam(name = "typeId") int id) {
//        System.out.println(id);
//        tagService.deleteByTagId(id);
//        return "redirect:/admin/types";
//    }
//
//    @GetMapping("/admin/countries")
//    public String countries(Model model){
//        model.addAttribute("countries", countryService.getAllCountries());
//        return "AdminController/admin-countries";
//    }
//
//    @PostMapping("/admin/countries/create")
//    public String countriesCreate(@RequestParam(name = "name") String name,
//                                  @RequestParam(name = "countryId") int countryId){
//        Country newCountry = new Country();
//        newCountry.setName(name);
//        newCountry.setCountryId(countryId);
//        countryService.saveCountry(newCountry);
//        return "redirect:/admin/countries";
//    }
//
//    @PostMapping("/admin/countries/delete")
//    public String countriesDelete(@RequestParam(name = "id") int id){
//        countryService.deleteByCountryId(id);
//        return "redirect:/admin/countries";
//    }
//
//    @GetMapping("/admin/products")
//    public String products(Model model){
//        model.addAttribute("products", blogService.getAll());
//        return "AdminController/admin-products";
//    }
//
//    @PostMapping("/admin/products/create")
//    public String createProduct(@RequestParam(name = "typeId") int typesId,
//                                @RequestParam(name = "name") String name,
//                                @RequestParam(name = "price") int price,
//                                @RequestParam(name = "weight") int weight,
//                                @RequestParam(name =  "description") String description,
//                                @RequestParam(name = "countryId") int countryId,
//                                @RequestParam(name = "imgPath") String imgPath){
//        System.out.println(imgPath);
//        Blog newBlog = new Blog();
//        newBlog.setTypeId(typesId);
//        newBlog.setName(name);
//        newBlog.setPrice(price);
//        newBlog.setWeight(weight);
//        newBlog.setDescription(description);
//        newBlog.setCountryId(countryId);
//        newBlog.setImgPath(imgPath);
//        blogService.saveProduct(newBlog);
//        return "redirect:/admin/products";
//    }
//
//    @PostMapping("/admin/products/delete")
//    private String deleteProduct(@RequestParam(name = "id")Long id){
//        blogService.deleteProductById(id);
//        return "redirect:/admin/products";
//    }
//
//    @GetMapping("/admin/users")
//    public String users(Model model){
//        List<User> users = userService.allUsers();
//        List<UserDto> usersDto = new ArrayList<>();
//
//        users.forEach(iterUser ->{
//            UserDto userDto = new UserDto(iterUser);
//            usersDto.add(userDto);
//        });
//        model.addAttribute("users", usersDto);
//
//        return "AdminController/admin-users";
//    }
//
//    @PostMapping("/admin/users/create")
//    public String createUser(@RequestParam(name = "email")String email,
//                             @RequestParam(name = "username")String username,
//                             @RequestParam(name = "password")String password,
//                             @RequestParam(name = "role")Long role){
//        User newUser = new User();
//        newUser.setEmail(email);
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        userService.saveUser(newUser, role);
//        return "redirect:/admin/users";
//    }
//
//    @PostMapping("/admin/users/delete")
//    public String deleteUser(@RequestParam(name = "userId")Long id){
//        userService.deleteUser(id);
//        return "redirect:/admin/users";
//    }


}
