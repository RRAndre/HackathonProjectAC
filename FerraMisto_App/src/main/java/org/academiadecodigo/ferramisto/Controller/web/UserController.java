package org.academiadecodigo.ferramisto.Controller.web;

import org.academiadecodigo.ferramisto.dto.UserDto;
import org.academiadecodigo.ferramisto.dto.UserDtoToUser;
import org.academiadecodigo.ferramisto.dto.UserToUserDto;
import org.academiadecodigo.ferramisto.exceptions.AssociationExistsException;
import org.academiadecodigo.ferramisto.exceptions.UserNotFoundException;

import org.academiadecodigo.ferramisto.persistence.models.User;
import org.academiadecodigo.ferramisto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserToUserDto userToUserDto;

    private UserDtoToUser userDtoToUser;



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }


    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }



    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listUsers(Model model){
        model.addAttribute("users", userToUserDto.convert(userService.list()));
        return "users/list";
    }

    /**
     * Adds a customer
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addUser(Model model) {
        model.addAttribute("customer", new UserDto());
        return "users/add-update";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editUser(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", userToUserDto.convert(userService.get(id)));
        return "users/add-update";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showUser(@PathVariable Integer id, Model model) {

        User customer = userService.get(id);

        model.addAttribute("customer", userToUserDto.convert(customer));

        return "users/show";
    }


    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws UserNotFoundException {

        System.out.println(bindingResult.getModel());

        if (bindingResult.hasErrors()) {
            return "user/add-update";
        }

        User savedCustomer = userService.save(userDtoToUser.convert(userDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        return "redirect:/user/" + savedCustomer.getId();
    }


    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=cancel")
    public String cancelSaveUser() {
        // we could use an anchor tag in the view for this, but we might want to do something clever in the future here
        return "redirect:/user/";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        User customer = userService.get(id);
        userService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + customer.getFirstName() + " " + customer.getLastName());
        return "redirect:/user";
    }

}
