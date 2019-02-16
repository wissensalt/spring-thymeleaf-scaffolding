package com.wissensalt.rnd.sts.web.webcomponent;

import lombok.*;

import java.io.Serializable;

/**
 * Created on 1/31/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButtonReset implements Serializable {

    private String text;

    @Builder.Default
    private String type = "btnReset";

    @Builder.Default
    private String className = "btn btn-default";

    @Builder.Default
    private String iconClassName = "fa fa-eraser";

    public static ButtonReset build(String p_Text) {
        ButtonReset result = new ButtonReset();
        result.setText(p_Text);
        return result;
    }
}
