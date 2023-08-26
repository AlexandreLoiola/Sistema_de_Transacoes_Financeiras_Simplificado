package com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Controller;

import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Dto.UserDto;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.rest.Form.UserForm;
import com.alexandreloiola.SistemadeTransferenciasFinanceirasSimplificado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok().body(userDtoList);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(
            @PathVariable("email") String email
    ) {
        UserDto userDto = userService.getUserDtoByEmail(email);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> insertUser(
            @Valid @RequestBody UserForm userForm
    ) {
        UserDto userDto = userService.insertUser(userForm);
        return ResponseEntity.ok().body(userDto);
    }
}
