package com.gmail.jahont.pavel.app.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.gmail.jahont.pavel.app.controller.command.Command;

public class DeleteCommand implements Command {

    @Override
    public void apply(HttpServletRequest request) {
        request.setAttribute("message", "  User was added successfully");
    }

}
