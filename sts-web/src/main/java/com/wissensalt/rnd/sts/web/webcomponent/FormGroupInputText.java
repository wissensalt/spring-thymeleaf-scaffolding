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
public class FormGroupInputText implements Serializable {

    @Builder.Default
    private String type = "text";
    private Label itemLabel;
    private InputText itemInput;
    private Boolean hasId;

    public static FormGroupInputText build(String p_Id, String p_FieldName, String p_PlaceHolder, String p_LabelText, Boolean p_Required) {
        FormGroupInputText result = new FormGroupInputText();
        result.setHasId(true);
        InputText txtCode = new InputText();
        txtCode.setId(p_Id);
        txtCode.setClassName("form-control");
        txtCode.setFieldName(p_FieldName);
        txtCode.setPlaceHolder(p_PlaceHolder);
        txtCode.setRequired(p_Required);

        Label labelCode = new Label();
        labelCode.setText(p_LabelText);

        result.setItemLabel(labelCode);
        result.setItemInput(txtCode);

        return result;
    }
}
