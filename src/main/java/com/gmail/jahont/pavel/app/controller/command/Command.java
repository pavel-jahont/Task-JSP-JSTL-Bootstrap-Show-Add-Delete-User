package com.gmail.jahont.pavel.app.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    void apply(HttpServletRequest request);

}
