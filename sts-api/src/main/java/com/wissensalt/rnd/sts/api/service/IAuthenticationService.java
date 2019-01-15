package com.wissensalt.rnd.sts.api.service;


import com.wissensalt.rnd.sts.shared.data.model.User;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IAuthenticationService extends LogoutHandler {

    User login(String p_UserName);

    void updateStatusLoggedIn(User p_User);

    void updateStatusLoggedOut(String p_UserName);
}
