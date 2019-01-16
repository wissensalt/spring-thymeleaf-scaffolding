package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestRegisterDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class RegisterClientImpl {

    @Value("${api.base-path}")
    private String apiBasePath;

    public ResponseDataDTO conductPost(RequestRegisterDTO requestRegisterDTO) {
        IRegisterClient registerClient = FeignBuilderFactory.createClient(IRegisterClient.class, apiBasePath);
        return registerClient.register(requestRegisterDTO);
    }
}
