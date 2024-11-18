package br.com.mateusfilpo.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo-bar")
public class Controller {

    @GetMapping
    public ResponseEntity<Void> testAuthentication() {
        return ResponseEntity.noContent().build();
    }
}
