package com.doxacore.login;

import java.util.Map;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import com.doxacore.login.services.AuthenticationService;
import com.doxacore.login.services.AuthenticationService3Impl;


public class AuthenticationInit implements Initiator {
	
	//services
    AuthenticationService authService = new AuthenticationService3Impl();

    public void doInit(Page page, Map<String, Object> args) throws Exception {

        UserCredential cre = authService.getUserCredential();
        if(cre==null || cre.isAnonymous()){
            Executions.sendRedirect("/corezul/login/login.zul");
            return;
        }
    }

}
