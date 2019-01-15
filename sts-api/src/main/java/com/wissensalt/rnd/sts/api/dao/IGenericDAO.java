package com.wissensalt.rnd.sts.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <DATA>
 */
@NoRepositoryBean
public interface IGenericDAO<DATA> extends JpaRepository<DATA, Long> {

    DATA findByCode(String p_Code);

    List<DATA> findByName(String p_Name);

}
