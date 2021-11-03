package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.TVDTO;
import project.service.TVService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/update")
public class UpdateTVController {

    private final TVService tvService;

    @GetMapping
    public String getTVForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("tvdto", tvService.findByID(id));
        return "update/update";
    }

    @PostMapping
    public void update(
            @ModelAttribute("tvdto") TVDTO tvdto,
            HttpServletResponse httpServletResponse) throws IOException {
        System.out.println(tvdto.getId());
        tvService.update(tvdto);

        httpServletResponse.sendRedirect("/show/all");
    }

    @GetMapping("/update_get_id")
    public String getId() {
        return "update/update_get_id";
    }
}
