package com.gmail.jahont.pavel.app.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.jahont.pavel.app.repository.model.UserInformation;

public interface UserInformationRepository extends GeneralRepository<UserInformation> {

    void updateAddressByUserId(Connection connection, int userId, String address) throws SQLException;

}
