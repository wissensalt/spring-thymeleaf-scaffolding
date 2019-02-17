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

    public static String build(String p_Id, String p_Name, String p_LabelText, String p_PlaceHolder, String p_Rows, String p_Cols, String p_Value, boolean p_Disabled) {
        String result = "<div class=\"form-group\">" +
                "                <label for=\""+p_Id+"\" class=\"col-sm-2 control-label\">"+p_LabelText+"</label>" +
                "                <div class=\"col-sm-10\">" +
                "                    <textarea class=\"form-control\" id=\""+p_Id+"\"  placeholder=\""+p_PlaceHolder+"\" name="+p_Name+" rows=\""+p_Rows+"\" cols=\""+p_Cols+"\" ";
        if (p_Disabled) {
            result += " disabled=disabled>"+p_Value+"</textarea></div></div>";
        }else {
            result += ">"+p_Value+"</textarea></div></div>";
        }
        return result;
    }
}
