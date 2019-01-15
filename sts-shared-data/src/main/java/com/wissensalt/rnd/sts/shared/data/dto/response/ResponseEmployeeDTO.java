package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Getter
@Setter
public class ResponseEmployeeDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String remarks;
    private Double salary;
    private Department department;

    static class Department {
        Long id;
        String code, name, remarks;
    }
}
