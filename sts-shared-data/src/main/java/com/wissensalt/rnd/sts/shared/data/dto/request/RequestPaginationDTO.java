package com.wissensalt.rnd.sts.shared.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 1/22/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class RequestPaginationDTO implements Serializable {
    private Integer offset;
    private Integer limit;
    private String order;
    private String sort;
}
