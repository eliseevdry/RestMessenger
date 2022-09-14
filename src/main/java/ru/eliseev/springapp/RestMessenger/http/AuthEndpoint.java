package ru.eliseev.springapp.RestMessenger.http;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eliseev.springapp.RestMessenger.domain.dto.requests.CreateUserRequest;
import ru.eliseev.springapp.RestMessenger.domain.dto.responses.UserResponse;
import ru.eliseev.springapp.RestMessenger.domain.exceptions.UserNotFoundException;
import ru.eliseev.springapp.RestMessenger.domain.models.User;
import ru.eliseev.springapp.RestMessenger.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthEndpoint {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") long id) throws UserNotFoundException {
        User user = userService.find(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @PostMapping
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {
        User user = userService.create(modelMapper.map(request, User.class));
        return modelMapper.map(user, UserResponse.class);
    }
}
