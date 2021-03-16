package in.webdevtoolkit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place-order")
public class PlaceOrderController {

    @GetMapping
    public String placeOrder(){
        return "Your Order is placed ...!!!";
    }
}
