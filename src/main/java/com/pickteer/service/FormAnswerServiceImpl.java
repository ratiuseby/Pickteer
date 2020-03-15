package com.pickteer.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickteer.models.FormAnswer;
import com.pickteer.repository.FormAnswerRepository;

@Service
public class FormAnswerServiceImpl implements FormAnswerService {

	@Autowired
	private FormAnswerRepository formAnswerRepository;

	@Override
	public void add(FormAnswer form) {
		formAnswerRepository.save(form);
	}

	@Override
	public void delete(long id) {
		List<FormAnswer> list = formAnswerRepository.findByFormId(id);
		if(!list.isEmpty()) {
			list.forEach(formAnswer -> formAnswerRepository.deleteById(formAnswer.getId()));
		}
	}

	@Override
	public List<FormAnswer> getFormAnswers(long userId) {
		return formAnswerRepository.findByUserId(userId);
	}

	@Override
	public FormAnswer getFormById(long id) {
		Optional<FormAnswer> optionalForm = formAnswerRepository.findById(id);

		if (!optionalForm.isPresent()) {
			return new FormAnswer();
		} else {
			return optionalForm.get();
		}
	}
	
	@Override
	public ByteArrayInputStream download(long id) {
		
		String[] columns = {"Name", "Email", "Points", "Form Name"};
		List<FormAnswer> list = formAnswerRepository.findByFormId(id);
		if(!list.isEmpty()) {
			
			// Create a Workbook
	        try(Workbook workbook = new XSSFWorkbook(); ) { // new HSSFWorkbook() for generating `.xls` file
		        // Create a Sheet
		        Sheet sheet = workbook.createSheet("Answers");
	
		        // Create a Font for styling header cells
		        Font headerFont = workbook.createFont();
		        headerFont.setBold(true);
		        headerFont.setFontHeightInPoints((short) 14);
		        headerFont.setColor(IndexedColors.RED.getIndex());
	
		        // Create a CellStyle with the font
		        CellStyle headerCellStyle = workbook.createCellStyle();
		        headerCellStyle.setFont(headerFont);
	
		        // Create a Row
		        Row headerRow = sheet.createRow(0);
	
		        // Create cells
		        for(int i = 0; i < columns.length; i++) {
		            Cell cell = headerRow.createCell(i);
		            cell.setCellValue(columns[i]);
		            cell.setCellStyle(headerCellStyle);
		        }
	
		        // Create Other rows and cells with employees data
		        int rowNum = 1;
		        for(FormAnswer formAnswer : list) {
		            Row row = sheet.createRow(rowNum++);
	
		            row.createCell(0)
		                    .setCellValue(formAnswer.getAnswers().get("Name"));
	
		            row.createCell(1)
		                    .setCellValue(formAnswer.getAnswers().get("Email"));
	
		            row.createCell(3)
		                    .setCellValue(formAnswer.getPoints());
		            
		            row.createCell(4)
	                		.setCellValue(formAnswer.getFormName());
	
		        }
		            
				// Resize all columns to fit the content size
		        for(int i = 0; i < columns.length; i++) {
		            sheet.autoSizeColumn(i);
		        }
	
		        // Write the output
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				workbook.write(outputStream);
		        
		        return new ByteArrayInputStream(outputStream.toByteArray());
	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}

}
