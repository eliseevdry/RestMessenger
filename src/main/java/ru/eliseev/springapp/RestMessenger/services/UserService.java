package ru.eliseev.springapp.RestMessenger.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eliseev.springapp.RestMessenger.domain.exceptions.InvalidDataException;
import ru.eliseev.springapp.RestMessenger.domain.exceptions.UserNotFoundException;
import ru.eliseev.springapp.RestMessenger.domain.models.User;
import ru.eliseev.springapp.RestMessenger.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User find(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) throws InvalidDataException {
        validateEmail(user.getEmail());
        enrichUser(user);
        return userRepository.save(user);
    }

    @Transactional
    public User update(long id, User user) throws InvalidDataException, UserNotFoundException {
        validateEmail(user.getEmail());
        User updated = find(id);
        modelMapper.map(user, updated);
        updated.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(updated);
    }

    private void enrichUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    private void validateEmail(String email) throws InvalidDataException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new InvalidDataException("This email already exists");
        }
    }
}