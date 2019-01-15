package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.dao.RoleDAO;
import com.wissensalt.rnd.sts.api.dao.UserDAO;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestRegisterDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.model.Role;
import com.wissensalt.rnd.sts.shared.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class RegistrationEndPointImpl implements IRegistrationEndPoint {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public ResponseDataDTO register(@RequestBody RequestRegisterDTO requestRegisterDTO) {
        ResponseDataDTO response = new ResponseDataDTO("201", "Failed Register");
        if (requestRegisterDTO.getName() != null && requestRegisterDTO.getUserName() != null && requestRegisterDTO.getPassword() != null) {
            User user = userDAO.findByCode(requestRegisterDTO.getUserName());
            if (user == null) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, 1);
                Date nextYear = cal.getTime();

                user = new User();
                user.setCode(requestRegisterDTO.getUserName());
                user.setName(requestRegisterDTO.getName());
                user.setPassword(passwordEncoder.encode(requestRegisterDTO.getPassword()));
                user.setEnabled(true);
                user.setExpiredDate(nextYear);
                user.setCredentialsExpiredDate(nextYear);
                user.setNonLocked(true);
                user.setLoginStatus(false);

                Role role = roleDAO.findByCode("ADMIN");
                if (role != null) {
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);

                    user.setRoles(roles);
                }

                userDAO.save(user);
                response = new ResponseDataDTO("200", "Success Register User");
            }
        }
        return response;
    }
}
