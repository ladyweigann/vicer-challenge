package com.challenge.viceri.repositories;

import com.challenge.viceri.entities.Priority;
import com.challenge.viceri.entities.Status;
import com.challenge.viceri.entities.Task;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final DataSource dataSource;

    public TaskRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Task> getAllPendingTasks(Integer priorityParam) {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT * FROM tasks t WHERE t.status = 1");

            if (priorityParam != null) {
                sqlBuilder.append(" AND t.priority = ").append(priorityParam);
            }

            String sql = sqlBuilder.toString();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                Integer priority = resultSet.getInt("priority");
                Integer status = resultSet.getInt("status");

                Task task = new Task(id, description, Priority.fromValue(priority), Status.fromValue(status));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public Task createTask(String descriptionRequest, Integer priorityRequest) {

        Task task = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO tasks(description, priority, status) VALUES (?, ?, 1)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setObject(1, descriptionRequest);
            statement.setObject(2, priorityRequest);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    Integer status = 1;
                    task = new Task(id, descriptionRequest, Priority.fromValue(priorityRequest), Status.fromValue(status));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    public Task updateTask(Long taskId, String updatedDescription, Integer updatedPriority) {

        Task task = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET description = ?, priority = ? WHERE id = ?")) {

            statement.setString(1, updatedDescription);
            statement.setInt(2, updatedPriority);
            statement.setLong(3, taskId);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM tasks t WHERE t.id = ?");
                    statement2.setLong(1, taskId);
                    ResultSet resultSet = statement2.executeQuery();

                    Long id = resultSet.getLong("id");
                    String description = resultSet.getString("description");
                    Integer priority = resultSet.getInt("priority");
                    Integer status = resultSet.getInt("status");
                    task = new Task(id, description, Priority.fromValue(priority), Status.fromValue(status));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }


}
