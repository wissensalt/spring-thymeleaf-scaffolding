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
public class FormGroupTextArea implements Serializable {
    @Builder.Default
    private String type = "textarea";
    private Label itemLabel;
    private InputTextArea itemInput;
    private Boolean hasId;

    public static FormGroupTextArea build(String p_Id, String p_FieldName, String p_Rows, String p_Cols, String p_LabelText) {
        FormGroupTextArea result = new FormGroupTextArea();
        result.setHasId(true);
        InputTextArea txtRemarks  = new InputTextArea();
        txtRemarks.setId(p_Id);
        txtRemarks.setClassName("form-control");
        txtRemarks.setPlaceHolder(null);
        txtRemarks.setRequired(false);
        txtRemarks.setFieldName(p_FieldName);
        txtRemarks.setRows(p_Rows);
        txtRemarks.setCols(p_Cols);

        Label labelRemarks = new Label();
        labelRemarks.setText(p_LabelText);
        result.setItemInput(txtRemarks);
        result.setItemLabel(labelRemarks);

        return result;
    }
}
