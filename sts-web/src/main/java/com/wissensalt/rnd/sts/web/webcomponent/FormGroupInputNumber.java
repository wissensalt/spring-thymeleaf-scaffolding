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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormGroupInputNumber implements Serializable {
    
    @Builder.Default
    private String type = "number";
    private Label itemLabel;
    private InputNumber itemInput;
    private Boolean hasId;
}
