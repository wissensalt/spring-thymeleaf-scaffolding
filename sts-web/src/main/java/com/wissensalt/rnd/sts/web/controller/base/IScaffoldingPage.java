package com.wissensalt.rnd.sts.web.controller.base;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationCustom;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 1/25/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <RESPONSE>
 */
public interface IScaffoldingPage<RESPONSE> {

    void initPage();

    @GetMapping(value = "")
    String displayIndex(Model p_Model, HttpServletRequest p_HttpServletRequest);

    @GetMapping(value = "/page")
    String displayIndexPaged(Model p_Model, @RequestParam("offset") int p_Offset, @RequestParam("size") int p_Size,HttpServletRequest p_HttpServletRequest);

    @PostMapping(value = "/search")
    String displaySearchResult(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestBody RequestPaginationCustom requestPaginationCustom);

    @GetMapping("/insertForm")
    String displayInsertForm(Model p_Model, HttpServletRequest p_HttpServletRequest);

    @GetMapping("/viewForm")
    String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id);

    @GetMapping("/updateForm")
    String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id);
}
