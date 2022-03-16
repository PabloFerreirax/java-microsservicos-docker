package com.java.back.end.casa.codigo.controller;

import com.java.back.end.casa.codigo.dto.DTOConverter;
import com.java.back.end.casa.codigo.service.UserService;
import dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<>();

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(new Date());
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(new Date());
        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(new Date());
        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }

    @GetMapping("/")
    public String getMensagem() {
        return "Spring boot is working!";
    }

    @GetMapping("/user")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpf(@PathVariable String cpf, @RequestParam(name = "key", required = true) String key) {
        return userService.findByCpf(cpf, key);
    }

    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(
            @RequestParam(name = "nome", required = true)
                    String nome) {
        return userService.queryByName(nome);
    }

    @PostMapping("/user")
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping("/user/{id}")
    public DTOConverter delete(@PathVariable Long id) {
        return userService.delete(id);
    }


}
