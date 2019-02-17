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

    /**
     *
     * <p>
     *     Build in Object Form
     * </p>
     * @param p_FieldName
     * @param p_LabelText
     * @return
     */
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

    /**
     * <p>
     *     Build in String form
     * </p>
     * @param p_LabelText
     * @param p_Name
     * @param p_Checked
     * @param p_Disabled
     * @return
     */
    public static String build(String p_LabelText, String p_Name, boolean p_Checked, boolean p_Disabled) {
        String checked = "";
        if (p_Checked) {
            checked = "checked=\"checked\"";
        }

        String disabled = "";
        if (p_Disabled) {
            disabled = "disabled=\"disabled\"";
        }
        return "<div class=\"form-group\">" +
                "                <div class=\"col-sm-offset-2 col-sm-10\">" +
                "                    <div class=\"checkbox\">" +
                "                        <label>" +
                "                            <input type=\"checkbox\" name=\""+p_Name+"\" " +checked+ " " +disabled+ "> "+p_LabelText+"" +
                "                        </label>" +
                "                    </div>" +
                "                </div>" +
                "            </div>";
    }
}
