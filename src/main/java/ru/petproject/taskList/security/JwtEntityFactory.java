package ru.petproject.taskList.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.petproject.taskList.entity.user.Role;
import ru.petproject.taskList.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(User user){
        return new JwtEntity(user.getId(), user.getUsername(), user.getName(), user.getPassword(), matToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }

    private static List<GrantedAuthority> matToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
