package com.doxacore.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.zul.Filedownload;

public class ReportExcel {

	private XSSFWorkbook workbook;
	private Sheet sheet;
	private String fileName;
	
	
	public ReportExcel(String fileName) {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Datos");
		this.fileName = fileName;
		
	}
	
	private void agregarTitulos(List<String[]> titulos) {

		int c = 0;

		for (String[] x : titulos) {
			
			Row headerRow = sheet.createRow(c);

			for (int i = 0; i < x.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(x[i]);
			}
			
			c++;

		}

	}

	private void agregarDatosCabecera(List<String[]> headersDatos) {

		
		CellStyle headerCellStyle = workbook.createCellStyle();

		//headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
		//headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle.setBorderTop(BorderStyle.THIN);
		headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
		headerCellStyle.setBorderBottom(BorderStyle.THIN);
		headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		headerCellStyle.setBorderLeft(BorderStyle.THIN);
		headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		
		XSSFFont font = this.workbook.createFont();
		font.setBold(true);
		headerCellStyle.setFont(font);
		
		int c = sheet.getLastRowNum()+1;

		for (String[] x : headersDatos) {
			
			Row headerRow = sheet.createRow(c);

			for (int i = 0; i < x.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(x[i]);
				cell.setCellStyle(headerCellStyle);
				sheet.autoSizeColumn(i);
			}
			
			
			
			c++;

		}

	}

	private void agregarDatos(List<Object[]> datos) {
		
		CellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		
		

		int c =sheet.getLastRowNum()+1;
		for (Object[] x : datos) {
			
			Row row = sheet.createRow(c);

			for (int i = 0; i < x.length; i++) {

				Cell cell = row.createCell(i);
				cell.setCellValue(x[i].toString());
				cell.setCellStyle(cellStyle);
				
				sheet.autoSizeColumn(i);

			}
			
			c++;

		}

	}

	public void descargar(List<String[]> titulos, List<String[]> headersDatos, List<Object[]> datos) {
		
		agregarTitulos(titulos);
		agregarDatosCabecera(headersDatos);
		agregarDatos(datos);
		
		//FileOutputStream out = new FileOutputStream(new File(fileName));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			this.workbook.write(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Filedownload.save(baos.toByteArray(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", this.fileName+".xlsx");

	}
}
