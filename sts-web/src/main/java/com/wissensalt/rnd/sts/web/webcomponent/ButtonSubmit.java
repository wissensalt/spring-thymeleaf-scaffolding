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
public class ButtonSubmit implements Serializable {
    private String text;

    @Builder.Default
    private String type = "btnSubmit";

    @Builder.Default
    private String className = "btn btn-info pull-right btnSave";

    @Builder.Default
    private String iconClassName = "fa fa-floppy-o";
}
