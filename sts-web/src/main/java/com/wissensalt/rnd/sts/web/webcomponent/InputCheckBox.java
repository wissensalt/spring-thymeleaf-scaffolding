package com.wissensalt.rnd.sts.web.webcomponent;

import lombok.*;

import java.io.Serializable;

/**
 * Created on 1/29/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class InputCheckBox implements Serializable {
    
    private String fieldName;
    private Boolean checked;
    private String id;
    private String stickyLabel;
}
