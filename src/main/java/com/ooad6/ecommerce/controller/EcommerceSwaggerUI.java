package com.ooad6.ecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class EcommerceSwaggerUI {

    @ApiIgnore
    @RequestMapping("/swagger")
    public void redirect(HttpServletResponse response) throws IOException, IOException {
        System.out.println("This is Swagger");
        response.sendRedirect("/swagger-ui.html");
    }

    @RequestMapping("/")
    public String redirect()  {
        System.out.println("This is Home");
        return "index";
    }
}


