package com.challenge.viceri.repositories;

import com.challenge.viceri.entities.Priority;
import com.challenge.viceri.entities.Status;
import com.challenge.viceri.entities.Task;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
