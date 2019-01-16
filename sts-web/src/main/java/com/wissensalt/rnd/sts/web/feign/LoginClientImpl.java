package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestFindByNameDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePrincipalDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created on 1/4/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class LoginClientImpl {

    @Value("${api.base-path}")
    private String apiBasePath;

    public ResponsePrincipalDTO conductPost(RequestLoginDTO p_RequestLogin) {
        ILoginClient loginClient = FeignBuilderFactory.createClient(ILoginClient.class, apiBasePath);
        return loginClient.login(p_RequestLogin);
    }

    public ResponseUserDTO conductPost(String p_Name) {
        ILoginClient loginClient = FeignBuilderFactory.createClient(ILoginClient.class, apiBasePath);
        RequestFindByNameDTO requestFindByNameDTO = new RequestFindByNameDTO();
        requestFindByNameDTO.setName(p_Name);
        return loginClient.findUser(requestFindByNameDTO);
    }
}
