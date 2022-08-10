package org.academiadecodigo.ferramisto.Controller.rest;

import org.academiadecodigo.ferramisto.dto.UserDto;
import org.academiadecodigo.ferramisto.dto.UserDtoToUser;
import org.academiadecodigo.ferramisto.dto.UserToUserDto;
import org.academiadecodigo.ferramisto.exceptions.AssociationExistsException;
import org.academiadecodigo.ferramisto.exceptions.UserNotFoundException;
import org.academiadecodigo.ferramisto.persistence.models.User;
import org.academiadecodigo.ferramisto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class    RestCustomerController {
    private UserService userService;
    private UserDtoToUser userDtoToUser;
    private UserToUserDto userToUserDto;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }



    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserDto> showUser(@PathVariable Integer id){
        User user = userService.get(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userToUserDto.convert(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add", ""})
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || userDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.save(userDtoToUser.convert(userDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/user" + savedUser.getId()).build();
        // set headers with the created path

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userDto.getId() != null && !userDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDto.setId(id);

        userService.save(userDtoToUser.convert(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {

            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //TODO: aplicaar aqui os caatchs que apagámos porque estavam a dar erro
    }
}
