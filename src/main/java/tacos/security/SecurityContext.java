package tacos.security;

import org.springframework.security.core.context.SecurityContextHolder;
import tacos.User;

public class SecurityContext {

    public static User getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        return user;
    }
}
