package ru.mirea.myblog_e.dto;

import org.springframework.context.annotation.Primary;
import ru.mirea.myblog_e.models.Blog;
import ru.mirea.myblog_e.models.Role;
import ru.mirea.myblog_e.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
//@Builder
public class UserDto implements Serializable {
    private Long id;

    @NotEmpty(message = "Поле фамилии не может быть пустым")
    private String username;

    @NotEmpty(message = "Поле телефона не может быть пустым")
    private String password;

//    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
//            message = "Формат поля email: x@x.xx")
    @NotEmpty(message = "Поле почты не может быть пустым")
    @Email(message = "Пожалуйста используйте валидную почту")
    private String email;

    private Set<Role> roles;

    private Set<Blog> blogs;

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
//        this.blogs = user.getBlogs();
    }
}
