package com.projeto.sistema.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeControle {

    @GetMapping("/")
    public String home() {
        return "administrativo";
    }
}