package com.wissensalt.rnd.sts.shared.data.mapper;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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

    @Mappings({
            @Mapping(target = "id", source = "employee.id"),
            @Mapping(target = "code", source = "employee.code"),
            @Mapping(target = "name", source = "employee.name"),
            @Mapping(target = "remarks", source = "employee.remarks"),
            @Mapping(target = "salary", source = "employee.salary"),
    })
    public abstract ResponseEmployeeDTO toEmployeeDTO(Employee employee);

    public abstract List<ResponseEmployeeDTO> toEmployeeDTO(List<Employee> employees);
}
