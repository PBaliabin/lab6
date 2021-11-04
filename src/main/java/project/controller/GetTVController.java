package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.TV;
import project.service.TVService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/show")
public class GetTVController {

    private final TVService tvService;

    @GetMapping(value = "/all", headers = {"Accept=text/html"})
    public String showAll(Model model) {
        model.addAttribute("tvs", tvService.findAll());
        return "show/all";
    }

    @GetMapping(value = "/all", headers = {"Accept=application/json"})
    @ResponseBody
    public Iterable<TV> showAllJson() {
        return tvService.findAll();
    }

    @GetMapping("/all_diagonal_wider_than")
    public String diagonalRequest(Model model) {
        model.addAttribute("diagonal", "");
        return "show/all_diagonal_wider_than";
    }

    @GetMapping(value = "/answer", headers = {"Accept=text/html"})
    public String getDiagonal(@RequestParam("diagonal") int diagonal,  Model model) {
        model.addAttribute("tvs", tvService.findAllByDiagonalGreaterThan(diagonal));
        return "show/all";
    }

    @GetMapping(value = "/answer", headers = {"Accept=application/json"})
    @ResponseBody
    public Iterable<TV> getDiagonalJson(@RequestParam("diagonal") int diagonal) {
        return tvService.findAllByDiagonalGreaterThan(diagonal);
    }

//    @RequestMapping(value="/{id}", method=RequestMethod.GET, headers={"Accept=application/json"})
//    public @ResponseBody Student getStudent(@PathVariable("id") int id) {
//        return studentsService.getStudentById(id);
//    }
//    @RequestMapping(value="/{id}", method=RequestMethod.GET, headers = {"Accept=text/html"})
//    public String getStudent(@PathVariable("id") int id, Model model) {
//        model.addAttribute(studentsService.getStudentById(id));
//        return "show";
//    }

}
