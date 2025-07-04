package com.mitocode.controller;

import com.mitocode.dto.UserDTO;
import com.mitocode.model.User;
import com.mitocode.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAllUsers() throws Exception{
        List<UserDTO> list = service.readAll().stream().map(e-> mapper.map(e,UserDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        UserDTO categoria = mapper.map(service.readById(id),UserDTO.class);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.save(mapper.map(dto,User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User category, @PathVariable("id") Integer id) throws Exception{
        User obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
