package com.wissensalt.rnd.sts.web.webcomponent;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2/5/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormGroupLOV implements Serializable {
    @Builder.Default
    private String type = "lov";

    private Label itemLabel;
    private String fieldName;
    private String className;
    private List<LOV> itemInput;
    private Boolean hasId;
    private String id;
}
