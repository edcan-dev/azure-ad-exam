package com.okysoft.azureadexam.services;

import com.okysoft.azureadexam.models.XlsxUser;
import com.okysoft.azureadexam.utils.UserCreationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationServiceImpl {

    @Autowired
    UserCreationUtil userCreationUtil;

    public boolean existByUserName(String userName) {
        return userCreationUtil.existByUserName(userName);
    }

    public  void createUser(XlsxUser xlsxUser) {
        userCreationUtil.createUser(xlsxUser);
    }

}
