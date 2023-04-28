package com.okysoft.azureadexam.services;

import com.okysoft.azureadexam.models.XlsxUser;
import com.okysoft.azureadexam.utils.ExcelManagementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XlsxServiceImpl {

    @Autowired
    ExcelManagementUtil excelManagementUtil;

    public List<XlsxUser> getUserFromSheet() {
        return excelManagementUtil.getUserFromSheet();
    }

}
