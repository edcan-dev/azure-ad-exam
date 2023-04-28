package com.okysoft.azureadexam.utils;

import com.microsoft.graph.models.WorkbookFunctionsOct2BinParameterSet;
import com.okysoft.azureadexam.models.XlsxUser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class ExcelManagementUtil {

    Logger logger = LoggerFactory.getLogger(ExcelManagementUtil.class);

    private Workbook workbook;

    public ExcelManagementUtil() {
        Path filePath = Path.of(System.getProperty("user.dir"),"src","main","resources","input","user_data.xlsx");
        try {
            FileInputStream file = new FileInputStream(new File(filePath.toUri()));
            workbook = new XSSFWorkbook(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<XlsxUser> getUserFromSheet() {
        List<XlsxUser> xlsxUsersList = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {

            try {

                XlsxUser user = new XlsxUser();
                user.setFirstName(row.getCell(0).getRichStringCellValue().getString());
                user.setLastName(row.getCell(1).getRichStringCellValue().getString());
                user.setFullName(row.getCell(7).getRichStringCellValue().getString());
                user.setEmail(row.getCell(5).getRichStringCellValue().getString());

                user.setPassword("Pass_".concat(user.getFirstName()).concat("_").concat(user.getLastName()));

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));

                    StringBuilder hexString = new StringBuilder(2 * hash.length);
                    for (byte b : hash) {
                        String hex = Integer.toHexString(0xff & b);
                        if (hex.length() == 1) { hexString.append('0');
                    }
                    hexString.append(hex);
                }
                String pass = user.getLastName().substring(0, 3) + user.getFirstName().substring(0, 3) + "_" + hexString.substring(0,6);
                user.setPassword(String.valueOf(pass));
                } catch (NoSuchAlgorithmException e) { }

                logger.info("===== User: ".concat(user.getFirstName()).concat(" || ".concat(user.getEmail()).concat("=====")));

                xlsxUsersList.add(user);

            } catch (NullPointerException e) {
                break;
            }
        }
        return xlsxUsersList;
    }
}
