package me.sanha.ohmymusicbackend.controller;

import lombok.RequiredArgsConstructor;
import me.sanha.ohmymusicbackend.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final StorageService storageService;

    @GetMapping
    public String getIndexPage(Model model) {

        model.addAttribute("sonFileNames", storageService.getSongFileNames());
        return "index";
    }
}
