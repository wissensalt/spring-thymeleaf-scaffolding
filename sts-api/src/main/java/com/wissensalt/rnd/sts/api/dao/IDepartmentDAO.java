package com.wissensalt.rnd.sts.api.dao;

import com.wissensalt.rnd.sts.shared.data.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IDepartmentDAO extends JpaRepository<Department, Long> {
}
