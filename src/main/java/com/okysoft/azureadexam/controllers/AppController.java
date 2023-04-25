package com.okysoft.azureadexam.controllers;

import com.okysoft.azureadexam.models.XlsxUser;
import com.okysoft.azureadexam.utils.ExcelManagementUtil;
import com.okysoft.azureadexam.utils.UserCreationUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    UserCreationUtil userCreationUtil;

    @Autowired
    ExcelManagementUtil excelManagementUtil;

    @RequestMapping(value = "/graphMeApi",method = RequestMethod.GET)
    public ResponseEntity<String> graphMeApi() throws MalformedURLException {

        List<XlsxUser> usersFromSheet = excelManagementUtil.getUserFromSheet();

        for (XlsxUser user : usersFromSheet) {
            userCreationUtil.createUser(user);
        }

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
