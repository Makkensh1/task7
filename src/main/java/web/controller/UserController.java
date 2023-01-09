package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.services.UserService;

@Controller
public class UserController {

    private UserService userServices;

    @Autowired
    public UserController(@Qualifier("us") UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users1", userServices.listUsers());
        return "users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, Model model) {
        User user = new User();
        model.addAttribute("user",user);
        user.setId(id);
        return "update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("user") User user){
        userServices.update(user);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userServices.deleteUserById(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("user") User user) {
        userServices.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }

}
