package com.gmail.jahont.pavel.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.jahont.pavel.app.controller.command.model.CommandEnum;
import com.gmail.jahont.pavel.app.service.UserService;
import com.gmail.jahont.pavel.app.service.impl.UserServiceImpl;
import com.gmail.jahont.pavel.app.service.model.UserDTO;

import static com.gmail.jahont.pavel.app.controller.constant.PagesConstant.PAGES_LOCATION;

public class AddUserServlet extends HttpServlet {

    public static final int USERNAME_LENGTH_VALIDATION = 40;

    private UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES_LOCATION + "/user_add.jsp");

        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO userDTO = getUserDTO(request);

        List<String> errors = validate(userDTO);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            doGet(request, response);
        } else {
            userService.addUser(userDTO);

            response.sendRedirect(request.getContextPath() + "/users?message=" + CommandEnum.ADD.name());
        }
    }

    private List<String> validate(UserDTO userDTO) {
        List<String> errors = new ArrayList<>();
        if (userDTO.getUsername() == null || userDTO.getUsername().length() > USERNAME_LENGTH_VALIDATION
                || userDTO.getUsername().isEmpty()) {
            errors.add("Username is not correct");
        }
        return errors;
    }

    private UserDTO getUserDTO(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(req.getParameter("username"));
        userDTO.setPassword(req.getParameter("password"));
        String ageString = req.getParameter("age");
        userDTO.setAge(Integer.parseInt(ageString));
        String activeString = req.getParameter("active");
        if (activeString != null) {
            switch (activeString) {
                case "on":
                    userDTO.setActive(true);
                    break;
                case "off":
                default:
                    userDTO.setActive(false);
                    break;
            }
        }
        userDTO.setAddress(req.getParameter("address"));
        userDTO.setTelephone(req.getParameter("telephone"));
        return userDTO;
    }

}
