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

    public static FormGroupInputNumber build(String p_Id, String p_FieldName, String p_PlaceHolder, String p_LabelText) {
        FormGroupInputNumber result = new FormGroupInputNumber();
        result.setHasId(true);
        InputNumber txtSalary = new InputNumber();
        txtSalary.setId(p_Id);
        txtSalary.setClassName("form-control");
        txtSalary.setFieldName(p_FieldName);
        txtSalary.setPlaceHolder(p_PlaceHolder);
        txtSalary.setRequired(true);

        Label labelSalary = new Label();
        labelSalary.setText(p_LabelText);

        result.setItemLabel(labelSalary);
        result.setItemInput(txtSalary);

        return result;
    }
}
