package com.wissensalt.rnd.sts.web.config;

import com.wissensalt.rnd.sts.shared.data.dto.response.AuthorityDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePrincipalDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseUserDTO;
import com.wissensalt.rnd.sts.shared.data.model.Role;
import com.wissensalt.rnd.sts.shared.data.model.User;
import com.wissensalt.rnd.sts.web.feign.LoginClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 5/30/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class SecurityBasicAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private LoginClientImpl authenticationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityBasicAuthenticationProvider.class);

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        RequestLoginDTO requestLoginDTO = new RequestLoginDTO();
        requestLoginDTO.setUserName(usernamePasswordAuthenticationToken.getName());
        requestLoginDTO.setPassword((String) usernamePasswordAuthenticationToken.getCredentials());
        ResponsePrincipalDTO principal = authenticationService.conductPost(requestLoginDTO);
        if (principal!= null) {
            User user = new User();
            user.setName(principal.getUserName());
            user.setPassword(principal.getPassword());
            user.setExpiredDate(principal.getExpiredDate());
            user.setCredentialsExpiredDate(principal.getCredentialsExpiredDate());
            user.setNonLocked(principal.getAccountNonLocked());
            user.setEnabled(principal.getEnabled());
            Set<Role> roles = new HashSet<>();
            for (AuthorityDTO authority : principal.getAuthorities()) {
                Role role = new Role();
                role.setName(authority.getAuthority());
                roles.add(role);
            }
            user.setRoles(roles);
            return user;
        }else {
            LOGGER.error("User is not found");
            throw new UsernameNotFoundException("User is not found");
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ResponseUserDTO user = authenticationService.conductPost(authentication.getName());
        if (user != null) {
            return super.authenticate(authentication);
        }else {
            LOGGER.error("User is not found");
        }
        return null;
    }
}
