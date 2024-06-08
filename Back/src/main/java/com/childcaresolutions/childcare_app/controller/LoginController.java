package com.childcaresolutions.childcare_app.controller;

import com.childcaresolutions.childcare_app.exeptions.InvalidCredentialsException;
import com.childcaresolutions.childcare_app.model.dto.request.RequestLogin;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseNanny;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseParent;
import com.childcaresolutions.childcare_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid @NotNull RequestLogin requestLogin,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Manejar errores de validación
            return ResponseEntity.badRequest().build();
        }

        try {
            Object response = authenticationService.login(requestLogin);
            if (response instanceof ResponseParent) {
                return ResponseEntity.ok((ResponseParent) response);
            } else if (response instanceof ResponseNanny) {
                return ResponseEntity.ok((ResponseNanny) response);
            } else {
                // Si el tipo de respuesta no es esperado, devolver una respuesta de error interna del servidor
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type");
            }
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    }
