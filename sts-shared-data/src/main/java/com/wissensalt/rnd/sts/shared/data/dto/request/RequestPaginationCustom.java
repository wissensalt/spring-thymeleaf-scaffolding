package com.wissensalt.rnd.sts.shared.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 2/20/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class RequestPaginationCustom implements Serializable {
    private int offset;
    private int limit;
    private String key;
    private String value;
}
