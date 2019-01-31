package com.wissensalt.rnd.sts.web.webcomponent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 1/29/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
public class FormGroupCheckBox implements Serializable {
    private Label itemLabel;
    private InputCheckBox itemInput;
}
