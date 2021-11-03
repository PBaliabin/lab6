package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.TVService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/delete")
public class DeleteTVController {

    private final TVService tvService;

    @PostMapping
    public String delete(@RequestParam("id") String id, Model model, HttpServletResponse httpServletResponse) throws URISyntaxException, IOException {
        tvService.delete(Integer.parseInt(id));

        httpServletResponse.sendRedirect("/show/all");
        return "delete";
    }

    @GetMapping()
    public String getTVForm(Model model){
        model.addAttribute("id", "");
        return "delete";
    }
}
