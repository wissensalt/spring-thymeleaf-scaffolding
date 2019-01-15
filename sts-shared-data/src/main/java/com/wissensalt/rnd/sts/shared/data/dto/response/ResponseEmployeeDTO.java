package com.wissensalt.rnd.sts.shared.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@NoArgsConstructor
@Getter
@Setter
public class ResponseEmployeeDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String remarks;
    private Double salary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResponseDepartmentDTO department;

    public ResponseEmployeeDTO(Long id, String code, String name, String remarks, Double salary, Long departmentId, String departmentCode, String departmentName, String departmentRemarks) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remarks = remarks;
        this.salary = salary;

        this.department = new ResponseDepartmentDTO(departmentId, departmentCode, departmentName, departmentRemarks);
    }

    public ResponseEmployeeDTO(Long id, String code, String name, String remarks, Double salary) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remarks = remarks;
        this.salary = salary;
    }
}

