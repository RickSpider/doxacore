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
import com.doxacore.util.UtilMetodos;

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


    @Listen("onClick=#login; onOK=#account; onOK=#password")
    public void doLogin(){
        String nm = account.getValue();
        String pd = UtilMetodos.getSHA256(password.getValue());

        if(!authService.login(nm,pd)){
            message.setValue("Usuario o Password incorrecto.");
            return;
        }
        UsuarioCredencial cre= authService.getUserCredential();
        message.setValue("Bienvenido, "+cre.getName());
        message.setSclass("");

        Executions.sendRedirect("/corezul/main/");
        //Executions.sendRedirect("/corezul/sidebar/");

    }
}
