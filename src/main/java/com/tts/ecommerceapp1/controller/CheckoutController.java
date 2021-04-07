package com.tts.ecommerceapp1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.ecommerceapp1.model.ChargeRequest;


@Controller
public class CheckoutController
{
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    
    @RequestMapping("/checkout")
    public String checkout(@RequestParam("amount") double amount, Model model)
    {
        amount *= 100;
        model.addAttribute("amount", (int)amount);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
}
