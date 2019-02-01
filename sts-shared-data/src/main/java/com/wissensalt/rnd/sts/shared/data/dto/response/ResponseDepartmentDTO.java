package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDepartmentDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String remarks;
    private Boolean status;
}
