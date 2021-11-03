package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.TVService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/show")
public class GetTVController {

    private final TVService tvService;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("tvs", tvService.findAll());
        return "show/all";
    }

    @GetMapping("/all_diagonal_wider_than")
    public String diagonalRequest(Model model) {
        model.addAttribute("diagonal", "");
        return "show/all_diagonal_wider_than";
    }

    @GetMapping("/answer")
    public String getDiagonal(@RequestParam("diagonal") String diagonal,  Model model) {
        model.addAttribute("tvs", tvService.findAllByDiagonalGreaterThan(Integer.parseInt(diagonal)));
        return "show/all";
    }
}
