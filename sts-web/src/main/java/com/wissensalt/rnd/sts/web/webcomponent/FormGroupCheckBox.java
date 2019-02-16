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
public class FormGroupCheckBox implements Serializable {

    @Builder.Default
    private String type = "checkbox";
    private Label itemLabel;
    private InputCheckBox itemInput;
    private Boolean hasId;

    public static FormGroupCheckBox build(String p_FieldName, String p_LabelText) {
        FormGroupCheckBox result = new FormGroupCheckBox();
        InputCheckBox checkboxStatus = new InputCheckBox();
        checkboxStatus.setFieldName(p_FieldName);
        checkboxStatus.setChecked(true);
        checkboxStatus.setStickyLabel(p_LabelText);
        result.setItemInput(checkboxStatus);

        result.setItemLabel(null);

        return result;
    }
}
