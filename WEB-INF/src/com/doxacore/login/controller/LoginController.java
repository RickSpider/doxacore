package com.doxacore.login.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import com.doxacore.login.UsuarioCredencial;
import com.doxacore.login.servicios.AuthenticationService;
import com.doxacore.login.servicios.AuthenticationService3Impl;

public class LoginController extends SelectorComposer<Component> {

    //wire components
    @Wire
    Textbox account;
    @Wire
    Textbox password;
    @Wire
    Label message;

    //services
    AuthenticationService authService = new AuthenticationService3Impl();


    @Listen("onClick=#login; onOK=#loginWin")
    public void doLogin(){
        String nm = account.getValue();
        String pd = password.getValue();

        if(!authService.login(nm,pd)){
            message.setValue("account or password are not correct.");
            return;
        }
        UsuarioCredencial cre= authService.getUserCredential();
        message.setValue("Welcome, "+cre.getName());
        message.setSclass("");

       // Executions.sendRedirect("/corezul/main/");
        Executions.sendRedirect("/corezul/adminTemplate/");

    }
}
