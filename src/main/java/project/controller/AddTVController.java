package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.TVDTO;
import project.service.TVService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/add")
public class AddTVController {

    private final TVService tvService;

    @PostMapping
    @ResponseBody
    public void postTV(@ModelAttribute("tvdto") TVDTO tvdto, HttpServletResponse httpServletResponse) throws URISyntaxException, IOException {
        tvService.add(tvdto);

        httpServletResponse.sendRedirect("/show/all");
        return;
    }

    @GetMapping
    public String getTVForm(Model model){
        model.addAttribute("tvdto", new TVDTO());
        return "add";
    }

}
