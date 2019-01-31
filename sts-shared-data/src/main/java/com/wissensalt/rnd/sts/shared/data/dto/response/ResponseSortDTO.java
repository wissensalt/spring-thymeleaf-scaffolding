package com.wissensalt.rnd.sts.shared.data.dto.response;

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
public class ResponseSortDTO implements Serializable {
    private boolean sorted;
    private boolean unsorted;
    private boolean empty;
}
