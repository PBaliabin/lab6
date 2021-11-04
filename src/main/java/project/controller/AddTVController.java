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
@RequestMapping("/add")
public class AddTVController {

    private final TVService tvService;

    @PostMapping(headers = {"Content-Type=application/x-www-form-urlencoded"})
    public void postTV(@ModelAttribute("tvdto") TVDTO tvdto, HttpServletResponse httpServletResponse) throws IOException {
        tvService.add(tvdto);

        httpServletResponse.sendRedirect("/show/all");
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<?> postTVJson(@RequestBody TVDTO tvdto) {
        tvService.add(tvdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public String getTVForm(Model model){
        model.addAttribute("tvdto", new TVDTO());
        return "add";
    }

}
