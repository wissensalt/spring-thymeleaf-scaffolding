package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created on 1/22/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
@Getter
@Setter
public class ResponsePaginationDTO<RESPONSE> {
    private List<RESPONSE> content;
//    private String pageable;
    private boolean last;
    private Integer totalPages;
    private Integer totalElements;
    private boolean first;
//    private ResponseSortDTO sort;
    private Integer numberOfElements;
    private Integer size;
    private Integer number;
    private boolean empty;
}
