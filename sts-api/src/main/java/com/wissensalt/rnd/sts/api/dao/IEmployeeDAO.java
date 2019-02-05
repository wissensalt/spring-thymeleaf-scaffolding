package com.wissensalt.rnd.sts.api.dao;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IEmployeeDAO extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO(e.id, e.code, e.name, e.remarks, e.status, e.salary, d.id, d.code, d.name, d.remarks, d.status) FROM Employee e left join e.department d")
    List<ResponseEmployeeDTO> findAllEmployee();
}
