package naufandi.service;

import naufandi.entity.Role;
import naufandi.entity.User;
import naufandi.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    UserResponse create(User user, Set<Role> roles);
}
