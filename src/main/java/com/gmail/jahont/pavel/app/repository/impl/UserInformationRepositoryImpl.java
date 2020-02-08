package com.gmail.jahont.pavel.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.gmail.jahont.pavel.app.repository.UserInformationRepository;
import com.gmail.jahont.pavel.app.repository.model.UserInformation;

public class UserInformationRepositoryImpl extends GeneralRepositoryImpl<UserInformation> implements UserInformationRepository {

    private static UserInformationRepository instance;

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInformation) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user_information(user_id, telephone, address) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setInt(1, userInformation.getUserId());
            statement.setString(2, userInformation.getTelephone());
            statement.setString(3, userInformation.getAddress());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating userInformation failed, no rows affected.");
            }
            return userInformation;
        }
    }

    @Override
    public List<UserInformation> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user_information WHERE user_id=?"
                )
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting userInformation failed, no rows affected.");
            }
        }
    }

    @Override
    public void updateAddressByUserId(Connection connection, int userId, String address) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE user_information SET address=? WHERE user_id=?"
                )
        ) {
            statement.setString(1, address);
            statement.setInt(1, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update userInformation failed, no rows affected.");
            }
        }
    }

}
