package com.wissensalt.rnd.sts.web.webcomponent;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
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
    private List<ResponseLOVDTO> itemInput;
    private Boolean hasId;
    private String id;

    public static FormGroupLOV build(String p_Id, String p_FieldName, List<ResponseLOVDTO> lovData, String p_LabelText) {
        FormGroupLOV result = new FormGroupLOV();
        result.setHasId(false);
        result.setId(p_Id);
        result.setFieldName(p_FieldName);
        result.setClassName("form-control");

        Label labelLovDepartment = new Label();
        labelLovDepartment.setText(p_LabelText);

        result.setItemInput(lovData);
        result.setItemLabel(labelLovDepartment);

        return result;
    }
}
