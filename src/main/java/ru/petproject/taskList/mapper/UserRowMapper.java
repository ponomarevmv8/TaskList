package ru.petproject.taskList.mapper;

import lombok.SneakyThrows;
import ru.petproject.taskList.entity.task.Task;
import ru.petproject.taskList.entity.user.Role;
import ru.petproject.taskList.entity.user.User;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRowMapper {

    @SneakyThrows
    public static User mapRow(ResultSet rs) {
        Set<Role> roles = new HashSet<>();
        while (rs.next()){
            roles.add(Role.valueOf(rs.getString("user_role_role")));
        }
        rs.beforeFirst();
        List<Task> tasks = TaskRowMapper.mapRows(rs);
        rs.beforeFirst();
        if(rs.next()) {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("user_name"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setRoles(roles);
            user.setTask(tasks);
            return user;
        }
        return null;
    }
}
