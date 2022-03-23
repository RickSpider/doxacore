package com.doxacore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UtilMetodos {
	
	public String getSql(String fileName) {
		
		return this.getSql("/WEB-INF/sql/", fileName);
		
	}
	
	public String getCoreSql(String fileName) {
		
		return this.getSql("/coresql/", fileName);
		
	}

	private String getSql(String folderPath, String fileName){
		
		String path = SystemInfo.SISTEMA_PATH_ABSOLUTO + folderPath + fileName;
		
		System.out.println("Este el el getSql "+path);

		StringBuffer sb = new StringBuffer();

		String line = "";
		
		File file = new File(path);
		
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			while ((line = br.readLine()) != null) {

				sb.append(line).append("\n");

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return sb.toString();

	}

}
