package net.lcc.configs;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toUserDetails(net.lcc.entities.User userCredentials) {

        return User.withUsername(userCredentials.getLogin())
                .password(userCredentials.getPassword())
                .roles(userCredentials.getRole())
                .build();
    }
}