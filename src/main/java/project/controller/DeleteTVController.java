package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.service.TVService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/delete")
public class DeleteTVController {

    private final TVService tvService;

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    public String delete(@RequestParam("id") String id, HttpServletResponse httpServletResponse) throws URISyntaxException, IOException {
        tvService.delete(Integer.parseInt(id));

        httpServletResponse.sendRedirect("/show/all");
        return "delete";
    }

    @DeleteMapping
    public ResponseEntity<?> deleteJson(@RequestParam("id") int id) {
        tvService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public String getTVForm(Model model){
        model.addAttribute("id", "");
        return "delete";
    }
}
