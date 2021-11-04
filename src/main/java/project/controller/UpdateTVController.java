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

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    public void update(
            @ModelAttribute("tvdto") TVDTO tvdto,
            HttpServletResponse httpServletResponse) throws IOException {
        tvService.update(tvdto);

        httpServletResponse.sendRedirect("/show/all");
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<?> updateJson(@RequestBody TVDTO tvdto) {
        tvService.update(tvdto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/update_get_id")
    public String getId() {
        return "update/update_get_id";
    }
}
