package org.javasource.services;

import lombok.RequiredArgsConstructor;
import org.javasource.exceptions.InvalidPasswordException;
import org.javasource.exceptions.UserNotFoundException;
import org.javasource.models.dto.UserLoginDTO;
import org.javasource.models.dto.UserRegisterDTO;
import org.javasource.models.entity.User;
import org.javasource.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public List<User> listAll() {
        return repository.findAll();
    }

    public User registerService(UserRegisterDTO register) {
        String senhaCriptografada = encoder.encode(register.getPassword());
        User user = new User();
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPassword(senhaCriptografada);

        return repository.save(user);
    }

    public User loginService(UserLoginDTO login) {
        User user = repository.findByEmail(login.getEmail())
                .orElseThrow(() -> new UserNotFoundException(login.getEmail()));

        if (!encoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return user;
    }
}
