package com.okysoft.azureadexam.utils;

import com.microsoft.graph.models.WorkbookFunctionsOct2BinParameterSet;
import com.okysoft.azureadexam.models.XlsxUser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelManagementUtil {

    private Workbook workbook;

    public ExcelManagementUtil() {
        Path filePath = Path.of(System.getProperty("user.dir"),"src","main","resources","input","users.xlsx");
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

        Map<Integer, List<String>> data = new HashMap<>();
        for (Row row : sheet) {
            XlsxUser user = new XlsxUser();
            user.setFirstName(String.valueOf(row.getCell(0)));
            user.setLastName(String.valueOf(row.getCell(1)));
            user.setAge((int) row.getCell(2).getNumericCellValue());
            user.setProfession(String.valueOf(row.getCell(3)));
            user.setGrade(String.valueOf(row.getCell(4)));
            user.setMaritalStatus(String.valueOf(row.getCell(5)));
            user.setCity(String.valueOf(row.getCell(6)));
            user.setJobTitle(String.valueOf(row.getCell(7)));

            //System.out.println(user.toString());

            xlsxUsersList.add(user);
        }
        return xlsxUsersList;
    }
}
