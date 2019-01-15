package com.wissensalt.rnd.sts.api.dao;

import com.wissensalt.rnd.sts.shared.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface RoleDAO extends JpaRepository<Role, Long> {

    Role findByCode(String p_Code);
}
