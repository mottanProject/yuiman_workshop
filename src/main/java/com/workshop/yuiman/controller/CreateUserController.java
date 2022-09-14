package com.workshop.yuiman.controller;

import com.workshop.yuiman.form.CreateInputForm;
import com.workshop.yuiman.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CreateUserController
{
    @Autowired
    LoginInfoService loginInfoService;

    @PostMapping("/api/v1/account-create")
    @ResponseBody
    public ResponseEntity result(@RequestBody CreateInputForm form)
    {
        loginInfoService.registAccount(form);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON));
        return new ResponseEntity(form, headers, HttpStatus.CREATED);
    }
}
