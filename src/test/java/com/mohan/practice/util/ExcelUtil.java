package com.mohan.practice.util;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class
ExcelUtil implements AutoCloseable {

    public static final short DEFAULT_SHEET_NUMBER = 0;



    protected Sheet getSheet(Workbook workbook, short sheetNum) {
        return Optional.ofNullable(workbook.getSheetAt(sheetNum)).orElse(null);
    }

    protected Workbook readFromExcel(String file) {

        Workbook workbook = null;

        try(FileInputStream fis = new FileInputStream(file)){

            workbook = WorkbookFactory.create(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return workbook;
    }

    protected void writeToExcel(String file, Workbook workbook) {

        try(FileOutputStream fos = new FileOutputStream(file)){

            workbook.write(fos);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void close() throws Exception {
        log.info("Excel manager resources closed successfully..");
    }
}

