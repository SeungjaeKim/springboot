package co.kr.springboot.controller;

import co.kr.springboot.dto.PostsResponseDto;
import co.kr.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping(value = "/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
