package com.wissensalt.rnd.sts.web.webcomponent;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import lombok.*;

import java.io.Serializable;

/**
 * Created on 2/5/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class LOV implements Serializable {
    private ResponseLOVDTO lovContent;
}
