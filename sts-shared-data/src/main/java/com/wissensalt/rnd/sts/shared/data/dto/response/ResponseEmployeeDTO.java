package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@ToString
@NoArgsConstructor
@Getter
@Setter
public class ResponseEmployeeDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String remarks;
    private Double salary;
    private Boolean status;
    private ResponseDepartmentDTO department;

    public ResponseEmployeeDTO(Long id, String code, String name, String remarks, Boolean status, Double salary, Long departmentId, String departmentCode, String departmentName, String departmentRemarks, Boolean departmentStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remarks = remarks;
        this.salary = salary;
        this.status = status;

        this.department = new ResponseDepartmentDTO(departmentId, departmentCode, departmentName, departmentRemarks, departmentStatus);
    }

    public ResponseEmployeeDTO(Long id, String code, String name, String remarks, Double salary, Boolean status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remarks = remarks;
        this.salary = salary;
        this.status = status;
    }
}

