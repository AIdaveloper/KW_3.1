package ru.mirea.myblog_e.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.myblog_e.dto.TagDto;
import ru.mirea.myblog_e.dto.BlogDto;
import ru.mirea.myblog_e.models.*;
import ru.mirea.myblog_e.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    private final BlogService blogService;

    private final TagService tagService;

    private final EmailService emailService;

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

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
        }
    }


    @GetMapping("/")
    public String main(Model model, Authentication authentication){
        System.out.println(getUserRole(authentication).contains("USER"));
        model.addAttribute("userRole", getUserRole(authentication));
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("selectedTags", null);
        model.addAttribute("searched", "Введите что хотите найти");
        model.addAttribute("blogs", blogService.getAll());

        return "Blogs";
    }

    @PostMapping("/")
    public String main2(@RequestParam(name = "search", required = false) String search,
                          @RequestParam(name = "tag", required = false) Integer tags,
                       Model model, Authentication authentication) throws Exception {
        model.addAttribute("userRole", getUserRole(authentication));
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("selectedTags", tags);
        if (!search.isEmpty()) {
            model.addAttribute("searched", search);
        } else {

            model.addAttribute("searched", "Введите что хотите найти");
        }
        model.addAttribute("blogs", blogService.getFilteredBlogs(search, tags, 0));
        return "redirect:/";
    }




    @GetMapping("/blog")
    public String blog(@RequestParam(name = "blogId", required = false) Long blogId,
                       Model model, Authentication authentication) {
        User user = getUser(authentication);
        model.addAttribute("userRole", getUserRole(authentication));
        Blog blog = blogService.getBlogById(blogId);
        model.addAttribute("isOwner", blog.getCreator().getId().equals(user.getId()));
        model.addAttribute("blog", blog);

        return "/blog";
    }

    @PostMapping("/blog")
    public String editBlog(@RequestParam(name = "blogId", required = false) Long blogId,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "subtitle", required = false) String subtitle,
                           @RequestParam(name = "text", required = false) String text,
                           @RequestParam(name = "tagId", required = false) Integer tagId,
                       Model model, Authentication authentication) {
        User user = getUser(authentication);

        model.addAttribute("userRole", getUserRole(authentication));
        Blog blog = blogService.getBlogById(blogId);
        blog.setTag(tagId);
        blog.setSummary(subtitle);
        blog.setText(text);
        blog.setTitle(title);
        blogService.saveBlog(blog);

        model.addAttribute("isOwner", blog.getCreator().getId().equals(user.getId()));
        model.addAttribute("blog", blog);

        return "redirect:/blog";
    }

    @GetMapping("/deleteBlog")
    public String deleteBlog(@RequestParam(name = "blogId", required = false) Long blogId,
                           Model model, Authentication authentication) {
        User user = getUser(authentication);
        Blog blog = blogService.getBlogById(blogId);
        if (user.getId().equals(blog.getCreator().getId())){
            blogService.deleteBlogById(blogId);
        }
        return "redirect:/blog";
    }

    @GetMapping("/createblog")
    public String createBlog(Model model, Authentication authentication) {

        User user = getUser(authentication);
        model.addAttribute("user", user);
        model.addAttribute("tags", tagService.getAllTags());
//        Tag a = new Tag();
//        a.getName()
        return "/create blog";
    }

    @PostMapping("/createblog")
    public String createBlog2(@RequestParam(name = "blogId", required = false) Long blogId,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "subtitle", required = false) String subtitle,
                           @RequestParam(name = "text", required = false) String text,
                           @RequestParam(name = "tagId", required = false) Integer tagId,
                           Model model, Authentication authentication) {
        User user = getUser(authentication);

        model.addAttribute("userRole", getUserRole(authentication));
        Blog blog = new Blog();
        blog.setCreator(user);
        blog.setTag(tagId);
        blog.setSummary(subtitle);
        blog.setText(text);
        blog.setTitle(title);
        blogService.saveBlog(blog);

        model.addAttribute("isOwner", blog.getCreator().getId().equals(user.getId()));
        model.addAttribute("blog", blog);

        return "redirect:/selfblogs";
    }

    @GetMapping("/selfblogs")
    public String selfBlogs(Model model, Authentication authentication) {

        User user = getUser(authentication);
        model.addAttribute("user", user);
        model.addAttribute("blogs", blogService.getBlogByCreatorId(user.getId()));
        return "/selfblogs";
    }

//
//    @PostMapping("/basket/changeBasket")
//    public String cBasket(@RequestParam(name = "productCount") int[] productCount,
//                          @RequestParam(name = "productId") Long[] productId,
//                          Authentication authentication){
//
//        System.out.println("product count" + Arrays.toString(productCount));
//        System.out.println("product id" + Arrays.toString(productId));
//
//        User user = getUser(authentication);
////        List<Basket> basket = basketService.getBasketByUserId(user.getId());
////        Long uId = user.getId();
//
//        for (int i = 0; i < productId.length; i++) {
//            Basket product = basketService.getBasketById(productId[i]);
////            System.out.println(productId[i]);
////            System.out.println(product);
//            if (product == null){
//                return "redirect:/basket";
//            }
//            System.out.println(product.getProductCount());
//            product.setProductCount(productCount[i]);
//            basketService.saveBasket(product);
//        }
//
//        return "redirect:/basket";
//    }
//
////    @PostMapping("/basket/changeBasket")
////    public String cdBasket(@RequestParam(name = "deleteProductId") Long productId,
////                          Authentication authentication){
////
////        System.out.println("product count" + Arrays.toString(productCount));
////        System.out.println("product id" + Arrays.toString(productId));
////
////        User user = getUser(authentication);
//////        List<Basket> basket = basketService.getBasketByUserId(user.getId());
//////        Long uId = user.getId();
////
////        return "redirect:/basket";
////    }
//
//    private static class DeleteProductId{
//        private Long id;
//    }
//
//    @PostMapping("/basket/deleteProductFromBasket")
//    public String dpfBasket(@RequestBody JsonNode body,
//                            Authentication authentication){
//
//        System.out.println("product id " + body.get("productId"));
//        System.out.println("product id " + body.get("Id"));
////        System.out.println("product id" + productId);
////
//        User user = getUser(authentication);
////        List<Basket> basket = basketService.getBasketByUserId(user.getId());
////        Long uId = user.getId();
//        Long productId = body.get("productId").asLong();
//        if (productId == null) {
//            return "redirect:/basket";
//        }
//        System.out.println(basketService.getBasketById(productId));
//        basketService.deleteBasketById(productId);
//
//        return "redirect:/basket";
//    }
//
//    @PostMapping("/basket/makeOrder")
//    public String makeOrder(@RequestParam(name = "adress") String adress,
//                            @RequestParam(name = "telephone") String telephone,
//                            Authentication authentication){
//
//        User user = getUser(authentication);
//        String userMessage = createMessageForUser(user);
//        String managerMessage = createMessageForManager(user, adress, telephone);
//        emailService.sendSimpleMailMessage(user.getEmail(), "SWEETSHOP", userMessage);
//        emailService.sendSimpleMailMessage("ilya201555555@gmail.com", "SWEETSHOP", managerMessage);
//
//
//        basketService.deleteAllBasketsByUserId(user.getId());
//
//
//        return "redirect:/products";
//    }


//    @PostMapping("/basket")
//    public String changeBasket(@RequestParam(name = "productCount") [int[String]] basketForm
//            Model model, Authentication authentication) {
//        System.out.println(basketForm);
//        return "redirect:/basket"
//    }
//    @PostMapping("/basket")
//    public String changeBasket(@RequestParam( value = "basket", required = false) ArrayList[ArrayList[int]String] basket,
//                                           Model model, Authentication authentication){
//
//    }

//    @GetMapping("/signup")
//    public String signup() {
//        return "/signup";
//    }
//
//    @PostMapping("/signup")
//    public String signCreate(HttpServletRequest request,
//                             @RequestParam(name = "email") String email,
//                             @RequestParam(name = "username") String username,
//                             @RequestParam(name = "password") String password,
//                             Model model) {
//        System.out.println("aaaaaaaaaaaaaaaaaaa");
//        if (userService.loadUserByUsername(username) != null) {
//            System.out.println("err");
//            model.addAttribute("Status", "user_exists");
//            return "/signup";
//        }
//        System.out.println("err2");
//        System.out.println(email + username);
//        userService.create(email, username, password);
//        authWithHttpServletRequest(request, username, password);
//        return "redirect:/";
//
//    }

}
