package com.wissensalt.rnd.sts.api.dao;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IDepartmentDAO extends JpaRepository<Department, Long> {

    @Query("SELECT new com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO(d.id, d.code, d.name, d.remarks) from Department d")
    List<ResponseDepartmentDTO> findAllDepartment();
}
