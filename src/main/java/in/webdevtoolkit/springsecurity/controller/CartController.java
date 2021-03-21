package in.webdevtoolkit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @GetMapping
    public String application(){
        return "Welcome to your own e-cart ..!!!";
    }
}
