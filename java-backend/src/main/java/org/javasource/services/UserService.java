package org.javasource.services;

import org.javasource.models.dto.UserLoginDTO;
import org.javasource.models.dto.UserRegisterDTO;
import org.javasource.models.entity.User;
import org.javasource.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

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
        User user = repository.findByEmail(login.getEmail()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(login.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        return user;
    }
}
