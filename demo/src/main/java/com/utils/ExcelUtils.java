package com.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtils {
	
	public static void createExcel(HttpServletResponse response,List<Map<String,String>> dataList,String[] key,String[] cellTitle,String filename) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet();
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell = null;
        for(int i = 0; i < cellTitle.length; i++ ){
        	cell = row.createCell(i);
        	cell.setCellType(Cell.CELL_TYPE_STRING);
        	cell.setCellValue(cellTitle[i]);
        }
        for(int i = 0 ; i < dataList.size(); i++){
        	row = sheet.createRow(i+1);
        	Map<String,String> map = dataList.get(i);
        	for(int j = 0;j< key.length;j++){
        		cell = row.createCell(j);
        		cell.setCellType(Cell.CELL_TYPE_STRING);
        		cell.setCellValue(String.valueOf(map.get(key[j])));
        	}
        }

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+filename);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/msexcel;charset=utf-8");
        wb.write(output);
        output.close();
    }
}
