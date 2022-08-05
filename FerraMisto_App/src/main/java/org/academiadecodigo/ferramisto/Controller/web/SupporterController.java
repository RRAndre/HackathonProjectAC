package org.academiadecodigo.ferramisto.Controller.web;

import org.academiadecodigo.ferramisto.dto.SupporterDto;
import org.academiadecodigo.ferramisto.dto.SupporterDtoToSupporter;
import org.academiadecodigo.ferramisto.dto.UserToUserDto;
import org.academiadecodigo.ferramisto.exceptions.TransactionInvalidException;
import org.academiadecodigo.ferramisto.exceptions.UserNotFoundException;
import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.services.SupporterService;
import org.academiadecodigo.ferramisto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/supporters")
public class SupporterController {
    private UserService userService;
    private SupporterService supporterService;

    private UserToUserDto userToUserDto;
    private SupporterDtoToSupporter supporterDtoToSupporter;

    @Autowired
    public void setSupporterService(SupporterService supporterService) {
        this.supporterService = supporterService;
    }

    @Autowired
    public void setSupporterDtoToSupporter(SupporterDtoToSupporter supporterDtoToSupporter) {
        this.supporterDtoToSupporter = supporterDtoToSupporter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{id}/supporter"})
    public String addSupporter(@PathVariable Integer id, @Valid @ModelAttribute("supporter") SupporterDto supporterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {

        if (bindingResult.hasErrors()) {
            return "redirect:/supporters/" + id;
        }

        try {
            Supporters supporter = supporterDtoToSupporter.convert((supporterDto));
            userService.addSupporter(id, supporter);
            //TODO: apaguei aqui uma linha
            return "redirect:/supporters/" + id;

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("failure", "Savings account must have a minimum value of 100 at all times");
            return "redirect:/supporters/" + id;
        }
    }
}
