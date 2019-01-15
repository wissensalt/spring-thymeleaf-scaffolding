package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * Created on 1/3/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataDTO implements Serializable {

    private String responseCode;
    private String responseMsg;
}
