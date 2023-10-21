package com.doxacore.components.finder;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

public interface FinderInterface {
	
	@Command	
	public void finderFilter(@BindingParam("filter") String filter, @BindingParam("finder") String finder);
	
	@Command
	public void onSelectetItemFinder(@BindingParam("id") Long id, @BindingParam("finder") String finder);
	
	public void generarFinders(@BindingParam("finder") String finder);

	public void inicializarFinders();
}
