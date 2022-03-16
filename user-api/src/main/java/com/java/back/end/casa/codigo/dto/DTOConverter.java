package com.java.back.end.casa.codigo.dto;

import com.java.back.end.casa.codigo.model.User;
import dto.UserDTO;

public class DTOConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO	= new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setKey(user.getKey());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}