package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class ResponsePrincipalDTO implements Serializable {
    private String userName;
    private List<AuthorityDTO> authorities;
    private String password;
    private Date expiredDate;
    private Date credentialsExpiredDate;
    private Boolean accountNonLocked;
    private Boolean enabled;
}
