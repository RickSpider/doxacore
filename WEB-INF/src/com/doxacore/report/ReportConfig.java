package com.doxacore.report;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

import com.doxacore.util.SystemInfo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;

public class ReportConfig {

	private Component view;
	private String source = "/reportTemplate/";
	private Map<String, Object> parameters;
    private JRDataSource dataSource;
    
    public ReportConfig(Component view,String source, JRDataSource dataSource, Map<String, Object> parameters) {
    	this.source += source;
    	this.view = view;
    	this.dataSource= dataSource;    
    	this.parameters = parameters;
   
    }
    
    public ReportConfig(Component view,String source, JRDataSource dataSource) {
    	this.source += source;
    	this.view = view;
    	this.dataSource= dataSource;
  
    }
  
 
    public String getSource() {
        return source;
    }
    
    public void showReport() {
    	
    	Window modal = (Window) Executions.createComponents("/doxacore/zul/report/reportModal.zul", this.view,
				null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();
    	
    	
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
