package com.doxacore.report;

import java.util.HashMap;
import java.util.Map;
 
import net.sf.jasperreports.engine.JRDataSource;

public class ReportConfig {

    private String source;
    private Map<String, Object> parameters;
    private JRDataSource dataSource;
    
 
    public ReportConfig(String source) {
    	this.source = source;
        parameters = new HashMap<String, Object>();
    }
 
    public String getSource() {
        return source;
    }
 
    public Map<String, Object> getParameters() {
        return parameters;
    }
 
    public JRDataSource getDataSource() {
        return dataSource;
    }
 
    public void setDataSource(JRDataSource reportDataSource) {
        this.dataSource = reportDataSource;
    }
	
}
