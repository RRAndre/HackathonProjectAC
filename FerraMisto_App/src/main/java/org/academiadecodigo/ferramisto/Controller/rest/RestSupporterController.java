package org.academiadecodigo.ferramisto.Controller.rest;

import org.academiadecodigo.ferramisto.dto.SupporterDto;
import org.academiadecodigo.ferramisto.dto.SupporterDtoToSupporter;
import org.academiadecodigo.ferramisto.dto.SupporterToSupporterDto;
import org.academiadecodigo.ferramisto.dto.UserDto;
import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.persistence.models.User;
import org.academiadecodigo.ferramisto.services.SupporterService;
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
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/supporters")
public class RestSupporterController {
    private UserService userService;
    private SupporterService supporterService;
    private SupporterToSupporterDto supporterToSupporterDto;
    private SupporterDtoToSupporter supporterDtoToSupporter;



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setSupporterService(SupporterService supporterService) {
        this.supporterService = supporterService;
    }
    @Autowired
    public void setSupporterToSupporterDto(SupporterToSupporterDto supporterToSupporterDto) {
        this.supporterToSupporterDto = supporterToSupporterDto;
    }
    @Autowired
    public void setSupporterDtoToSupporter(SupporterDtoToSupporter supporterDtoToSupporter) {
        this.supporterDtoToSupporter = supporterDtoToSupporter;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/all", ""})
    public ResponseEntity<List<SupporterDto>> listSupporters() {

        List<SupporterDto> supporterDto = supporterService.list().stream()
                .map(supporters -> supporterToSupporterDto.convert(supporters))
                .collect(Collectors.toList());

        return new ResponseEntity<>(supporterDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<SupporterDto> showSupporter(@PathVariable Integer id){
        Supporters supporter = supporterService.get(id);

        if(supporter == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supporterToSupporterDto.convert(supporter), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public ResponseEntity<?> addSupporter(@Valid @RequestBody SupporterDto supporterDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || supporterDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Supporters savedSupporter = supporterService.save(supporterDtoToSupporter.convert(supporterDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/user" + savedSupporter.getId()).build();
        // set headers with the created path

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<SupporterDto> editSupporter(@Valid @RequestBody SupporterDto supporterDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (supporterDto.getId() != null && !supporterDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        supporterDto.setId(id);

        supporterService.save(supporterDtoToSupporter.convert(supporterDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<SupporterDto> deleteSupporter(@PathVariable Integer id) {

        supporterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //TODO: aplicaar aqui os caatchs que apagámos porque estavam a dar erro
    }
}




























