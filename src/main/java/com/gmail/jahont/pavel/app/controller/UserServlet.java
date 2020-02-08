package com.gmail.jahont.pavel.app.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.jahont.pavel.app.controller.command.Command;
import com.gmail.jahont.pavel.app.controller.command.impl.AddCommand;
import com.gmail.jahont.pavel.app.controller.command.impl.DeleteCommand;
import com.gmail.jahont.pavel.app.controller.command.model.CommandEnum;
import com.gmail.jahont.pavel.app.service.TableService;
import com.gmail.jahont.pavel.app.service.UserService;
import com.gmail.jahont.pavel.app.service.impl.TableServiceImpl;
import com.gmail.jahont.pavel.app.service.impl.UserServiceImpl;
import com.gmail.jahont.pavel.app.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gmail.jahont.pavel.app.controller.constant.PagesConstant.PAGES_LOCATION;

public class UserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private UserService userService = UserServiceImpl.getInstance();
    private TableService tableService = TableServiceImpl.getInstance();

    @Override
    public void init() {
        tableService.deleteAllTables();
        tableService.createAllTables();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        addMessageIfApplied(request);

        List<UserDTO> users = userService.findAll();
        request.setAttribute("users", users);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES_LOCATION + "/users.jsp");

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        userService.deleteUserById(userId);
        resp.sendRedirect(getServletContext().getContextPath() + "/users?message=" + CommandEnum.DELETE.name());
    }

    private void addMessageIfApplied(HttpServletRequest request) {
        String messageCommand = request.getParameter("message");
        if (messageCommand != null) {
            try {
                Command command = getCommand(messageCommand);
                command.apply(request);
            } catch (IllegalArgumentException e) {
                logger.error("Command not found");
            }
        }
    }

    private Command getCommand(String messageCommand) {
        CommandEnum commandEnum = CommandEnum.valueOf(messageCommand.toUpperCase());
        switch (commandEnum) {
            case DELETE:
                return new DeleteCommand();
            case ADD:
                return new AddCommand();
            default:
                throw new UnsupportedOperationException("Invalid command name");
        }
    }

}
