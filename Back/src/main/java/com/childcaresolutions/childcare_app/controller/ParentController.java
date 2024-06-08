/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.childcaresolutions.childcare_app.controller;

import com.childcaresolutions.childcare_app.exeptions.EmailAlreadyExistsException;
import com.childcaresolutions.childcare_app.exeptions.EntityNotFoundException;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateParent;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditParent;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseParent;
import com.childcaresolutions.childcare_app.service.ParentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@AllArgsConstructor
@Validated
@RequestMapping(value = "/api/parents")
@RestController
public class ParentController {

    private ParentService parentService;

    @PostMapping("/register")
    public ResponseEntity<ResponseParent> createParent(@Valid @RequestBody RequestCreateParent requestCreateParent) {
        ResponseParent responseParent = parentService.createParent(requestCreateParent);
        return new ResponseEntity<>(responseParent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseParent> getParent (@PathVariable Long id){
        ResponseParent responseParent = parentService.readParent(id);
        return new ResponseEntity<>(responseParent, HttpStatus.OK);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseParent> updateParent(@Valid @RequestBody RequestEditParent requestEditParent) {
        ResponseParent responseParent = parentService.updateParent(requestEditParent);
        return new ResponseEntity<>(responseParent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> daleteParent(@PathVariable Long id) {
        parentService.toogleDeleteParent(id);
        return ResponseEntity.ok("Parent with Id: " + id + "has been deleted successfully");
    }
}
