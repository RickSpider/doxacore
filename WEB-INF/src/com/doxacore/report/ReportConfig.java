package com.doxacore.report;

import java.util.HashMap;
import java.util.Map;

import com.doxacore.util.SystemInfo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;

public class ReportConfig {

	private String source = "/reportTemplate/jasperreport.jasper";
   // private Map<String, Object> parameters;
    private JRDataSource dataSource;
   
 
    public ReportConfig(String source) {
    	this.source = source;
       /* parameters = new HashMap<String, Object>();
        parameters.put("ReportTitle", "Address Report");
        parameters.put("DataFile", "CustomDataSource from java xx");*/
    }
  
 
    public String getSource() {
        return source;
    }
 
   /* public Map<String, Object> getParameters() {
        return parameters;
    }
 */
    public JRDataSource getDataSource() {
        return dataSource;
    }
 
    public void setDataSource(JRDataSource reportDataSource) {
        this.dataSource = reportDataSource;
    }
	
}
