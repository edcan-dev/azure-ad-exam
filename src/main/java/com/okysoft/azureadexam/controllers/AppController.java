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


    List<XlsxUser> usersFromSheet;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<String> readExcel() throws MalformedURLException {

        usersFromSheet = excelManagementUtil.getUserFromSheet();

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/create/accounts",method = RequestMethod.POST)
    public ResponseEntity<String> graphMeApi() throws MalformedURLException {

        for (XlsxUser user : usersFromSheet) {
            userCreationUtil.createUser(user);
            System.out.println("----------------------------------------------------------------" + user.getUserName());
        }
        //userCreationUtil.createUser(usersFromSheet.get(10));
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
