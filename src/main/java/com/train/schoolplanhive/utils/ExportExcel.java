package com.train.schoolplanhive.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExportExcel {
	/**
     * @param sheetName 工作表的名字
     * @param column  列名
     * @param data  需要导出的数据    ( map的键定义为列的名字 一定要和column中的列明保持一致  )
     * @param response
     */
    public static void exportExcel(String sheetName, List<String> column, List<Map<String,Object>> data, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//创建工作薄
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//创建sheet
		HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
		// 表头
		HSSFRow headRow = sheet.createRow(0);
		for (int i = 0; i < column.size(); i++){
			headRow.createCell(i).setCellValue(column.get(i));
		}
		
		for (int i = 0; i < data.size(); i++) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			for (int x = 0; x < column.size(); x++) {
				dataRow.createCell(x).setCellValue(data.get(i).get(column.get(x))==null?"":data.get(i).get(column.get(x)).toString());
			}
		}

		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream outputStream = response.getOutputStream();
		try {
			   //获取浏览器名称
			   String agent=request.getHeader("user-agent");
			   String filename=sheetName+".xls";
			   //不同浏览器需要对文件名做特殊处理

			    filename = URLEncoder.encode(filename, "utf-8");
				filename = filename.replace("+"," ");

			   //推送浏览器

				response.setHeader("Content-Disposition","attachment;filename="+filename);
			   	hssfWorkbook.write(outputStream);
			  } catch (Exception e) {
			   e.printStackTrace();
			  } finally {
				outputStream.close();
		}
    }

}
