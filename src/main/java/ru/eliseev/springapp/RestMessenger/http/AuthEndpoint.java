package ru.eliseev.springapp.RestMessenger.http;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eliseev.springapp.RestMessenger.domain.dto.requests.CreateUserRequest;
import ru.eliseev.springapp.RestMessenger.domain.dto.requests.UpdateUserRequest;
import ru.eliseev.springapp.RestMessenger.domain.dto.responses.UserResponse;
import ru.eliseev.springapp.RestMessenger.domain.exceptions.InvalidDataException;
import ru.eliseev.springapp.RestMessenger.domain.exceptions.UserNotFoundException;
import ru.eliseev.springapp.RestMessenger.domain.models.User;
import ru.eliseev.springapp.RestMessenger.services.UserService;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthEndpoint {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) throws InvalidDataException {
        User user = userService.create(modelMapper.map(request, User.class));
        return modelMapper.map(user, UserResponse.class);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") long id) throws UserNotFoundException {
        User user = userService.find(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable("id") long id,
                               @RequestBody @Valid UpdateUserRequest request) throws InvalidDataException, UserNotFoundException {
        User user = userService.update(id, modelMapper.map(request, User.class));
        return modelMapper.map(user, UserResponse.class);
    }
}
