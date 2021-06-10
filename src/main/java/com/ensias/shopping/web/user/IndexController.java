package com.ensias.shopping.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String toIndex() {
        return "mall/index";
    }


    @RequestMapping("/")
    public String index(){
        return "forward:/index.html";
    }

}
