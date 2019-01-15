package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created on 1/3/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@ToString
@Getter
@Setter
public class RequestLoginDTO implements Serializable {

    private String userName;
    private String password;
}
