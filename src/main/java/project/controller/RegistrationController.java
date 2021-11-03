package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.UserDTO;
import project.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "authentication/registration";
    }

    @PostMapping
    public void getRegistrationForm(@ModelAttribute("user") UserDTO userDTO, HttpServletResponse httpServletResponse) throws IOException {
        userService.addUser(userDTO);
        httpServletResponse.sendRedirect("/login");
    }
}
