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

    /**
     *
     * <p>
     *     Build Object Based Input Text
     * </p>
     * @param p_Id
     * @param p_FieldName
     * @param p_PlaceHolder
     * @param p_LabelText
     * @param p_Required
     * @return
     */
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

    /**
     * <p>
     *     Build String base Input Text
     * </p>
     * @param p_Id
     * @param p_LabelText
     * @param p_Required
     * @param p_Value
     * @param p_ReadOnly
     * @return
     */
    public static String build(String p_Id, String p_Name, String p_LabelText, String p_PlaceHolder, boolean p_Required, String p_Value, String p_ReadOnly, boolean disabled) {
        String required = "";
        if (p_Required) {
            required = "required=\"required\"";
        }
        String result =  "<div class=\"form-group\">" +
                "                <label for=\""+p_Id+"\" class=\"col-sm-2 control-label\">"+p_LabelText+"</label>" +
                "                <div class=\"col-sm-10\">" +
                "                    <input type=\"text\" class=\"form-control\" id=\""+p_Id+"\"  placeholder=\""+p_PlaceHolder+"\" name="+p_Name+" "+required+" value=\""+p_Value+"\" "+p_ReadOnly;
        if (disabled) {
            result += " disabled=\"disabled\"></div></div>";
        }else {
            result += "></div></div>";
        }
        return result;
    }
}
