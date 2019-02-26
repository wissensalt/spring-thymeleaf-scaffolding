package com.wissensalt.rnd.sts.shared.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 2/4/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLOVDTO implements Serializable {

    private String key;
    private String value;

    public ResponseLOVDTO(Long key, String value) {
        this.key = String.valueOf(key);
        this.value = value;
    }
}
