package com.wissensalt.rnd.sts.shared.data.mapper;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.model.Department;
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
public abstract class DepartmentMapper {

    @Mappings({
            @Mapping(target = "id", source = "department.id"),
            @Mapping(target = "code", source = "department.code"),
            @Mapping(target = "name", source = "department.name"),
            @Mapping(target = "remarks", source = "department.remarks")
    })
    public abstract ResponseDepartmentDTO toDepartmentDTO(Department department);

    public abstract List<ResponseDepartmentDTO> toDepartmentDTO(List<Department> departments);
    }
