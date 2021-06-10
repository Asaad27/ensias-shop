package com.ensias.shopping.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ensias.shopping.entity.User;
import com.ensias.shopping.entity.res.ResultBean;
import com.ensias.shopping.service.UserService;
import com.ensias.shopping.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/toRegister.html")
    public String toRegister() {
        return "mall/user/register";
    }


    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "mall/user/login";
    }


    @RequestMapping("/login.do")
    public void login(String username,
                      String password,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        User user = userService.checkLogin(username, password);
        if (user != null) {
            //Connexion réussie à la redirection vers la page d'accueil
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/mall/index.html");
        } else {
            throw new LoginException("Échec de la connexion! L'identifiant ou le mot de passe est incorrect");
        }

    }

    @RequestMapping("/register.do")
    public void register(String username,
                         String password,
                         String name,
                         String phone,
                         String email,
                         String addr,
                         HttpServletResponse response) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddr(addr);
        userService.create(user);
        //Redirection vers la page de connexion après inscription
        response.sendRedirect("/mall/user/toLogin.html");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/mall/index.html");
    }


    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String username){
        List<User> users = userService.findByUsername(username);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }


    @RequestMapping("/error.html")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }
}
