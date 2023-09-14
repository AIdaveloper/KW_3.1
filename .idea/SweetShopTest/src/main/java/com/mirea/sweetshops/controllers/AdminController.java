package com.mirea.sweetshops.controllers;

import com.mirea.sweetshops.models.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.services.BlogService;
import com.mirea.sweetshops.services.TagService;
import com.mirea.sweetshops.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
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


    @GetMapping("admin/users")
    public String users(Model model, Authentication authentication) {
        User user = getUser(authentication);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("userRole", getUserRole(authentication));
        return "users";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam(name = "userId")Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/tags")
    public String types(Model model, Authentication authentication) {
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("userRole", getUserRole(authentication));
        return "tags";
    }
    @PostMapping("/admin/tags/create")
    public String typesCreate(@RequestParam(name = "tagName") String name,
                              @RequestParam(name = "tagId") Long tid) {
        Tag newTag = new Tag();
        newTag.setName(name);
        tagService.saveTag(newTag);
        return "redirect:/admin/tags";
    }
    @PostMapping("/admin/tags/delete")
    public String typesDelete(@RequestParam(name = "Id") Long id) {
        System.out.println(id);
        tagService.deleteById(id);
        return "redirect:/admin/tags";
    }
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
