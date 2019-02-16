package com.wissensalt.rnd.sts.api.mapper;

import com.wissensalt.rnd.sts.api.dao.IDepartmentDAO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.mapper.MappingConfig;
import com.wissensalt.rnd.sts.shared.data.model.Department;
import com.wissensalt.rnd.sts.shared.data.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
@Mapper(config = MappingConfig.class)
public abstract class EmployeeMapper {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @Mappings({
            @Mapping(target = "id", source = "employee.id"),
            @Mapping(target = "code", source = "employee.code"),
            @Mapping(target = "name", source = "employee.name"),
            @Mapping(target = "remarks", source = "employee.remarks"),
            @Mapping(target = "salary", source = "employee.salary"),
            @Mapping(target = "status", source = "employee.status"),
            @Mapping(target = "department", source = "employee.department"),
            @Mapping(target = "departmentId", ignore = true),
    })
    public abstract ResponseEmployeeDTO toEmployeeDTO(Employee employee);

    public abstract List<ResponseEmployeeDTO> toEmployeeDTO(List<Employee> employees);

    @Mappings({
            @Mapping(target = "code", source = "employee.code"),
            @Mapping(target = "name", source = "employee.name"),
            @Mapping(target = "remarks", source = "employee.remarks"),
            @Mapping(target = "salary", source = "employee.salary"),
            @Mapping(target = "status", source = "employee.status"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "department", source = "employee.departmentId", qualifiedByName = "departmentById")
    })
    public abstract Employee requestToDepartment(RequestInsertEmployeeDTO employee);

    @Named("departmentById")
    Department departmentById(Long departmentId) {
        return departmentDAO.findById(departmentId).get();
    }


}
