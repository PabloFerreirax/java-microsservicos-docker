package com.java.back.end.casa.codigo.service;

import com.java.back.end.casa.codigo.dto.DTOConverter;
import exception.UserNotFoundException;
import com.java.back.end.casa.codigo.model.User;
import com.java.back.end.casa.codigo.repository.UserRepository;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        Optional<User> usuario = userRepository.findById(userId);
        return usuario.map(DTOConverter::convert).orElse(null);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        User user = User.convert(userDTO);
        user.setDataCadastro(new Date());
        userRepository.save(user);
        return DTOConverter.convert(user);
    }

    public DTOConverter delete(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> userRepository.delete(value));
        return null;
    }

    public UserDTO findByCpf(String cpf, String key) {
        User user = userRepository.findByCpfAndKey(cpf, key);
        if (user == null)
            throw new UserNotFoundException();
        return DTOConverter.convert(user);
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

}
