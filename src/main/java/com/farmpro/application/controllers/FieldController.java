package com.farmpro.application.controllers;

import com.farmpro.application.entities.Field;
import com.farmpro.application.services.FieldService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/field")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from localhost:3000
public class FieldController {

    private final FieldService fieldService;


    @GetMapping({"", "/"})
    public ResponseEntity<List<Field>> getAllFields(){
         List<Field> retrievedFieldList =  fieldService.getAllFields();
        return new ResponseEntity<List<Field>>(retrievedFieldList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable("id") Long id, HttpServletResponse response){
        Field retrievedField = fieldService.getFieldById(id);

        // Create a new cookie
        Cookie cookie = new Cookie("yourCookieName", "yourCookieValue");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        HttpHeaders headers = new HttpHeaders();



        return new ResponseEntity<>(retrievedField, headers, HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Field> createField(@RequestBody Field field){
        Field savedField = fieldService.createField(field);
        return new ResponseEntity<Field>(savedField,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable("id") Long id, @RequestBody Field field) {
        Field savedField = fieldService.updateField(id,field);
        return new ResponseEntity<Field>(savedField,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable("id") Long id) {
        fieldService.deleteField(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
