package com.challenge.viceri.repositories;

import com.challenge.viceri.entities.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(id, name, email, password);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User createNewUser(String name, String email, String password) {
        User user = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name, email, password) VALUES (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);

                    try (PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
                        statement2.setLong(1, id);
                        ResultSet resultSet = statement2.executeQuery();

                        if (resultSet.next()) {
                            String nameDB = resultSet.getString("name");
                            String emailDB = resultSet.getString("email");
                            String passwordDB = resultSet.getString("password");
                            user = new User(id, nameDB, emailDB, passwordDB);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User existsByEmail(String email) {
        User user = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM users u WHERE u.email = ?")) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long idDB = resultSet.getLong("id");
                String nameDB = resultSet.getString("name");
                String emailDB = resultSet.getString("email");
                String passwordDB = resultSet.getString("password");
                user = new User(idDB, nameDB, emailDB, passwordDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
