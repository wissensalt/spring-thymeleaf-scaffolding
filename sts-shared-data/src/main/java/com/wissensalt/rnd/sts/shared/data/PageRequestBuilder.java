package com.wissensalt.rnd.sts.shared.data;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created on 1/22/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class PageRequestBuilder {

    public static PageRequest build(RequestPaginationDTO p_PaginationDTO) {
        if (p_PaginationDTO.getOffset() != null && p_PaginationDTO.getLimit() != null && p_PaginationDTO.getOrder() != null && p_PaginationDTO.getSort() != null) {
            return PageRequest.of(
                    p_PaginationDTO.getOffset(),
                    p_PaginationDTO.getLimit(),
                    Sort.Direction.fromString(p_PaginationDTO.getOrder()),
                    p_PaginationDTO.getSort()
            );
        }else {
            return null;
        }
    }
}
