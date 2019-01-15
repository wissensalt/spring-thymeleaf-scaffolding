package com.wissensalt.rnd.sts.shared.data.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Where(clause = "status=true")
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "remarks")
    private String remarks;

    @Where(clause = "status=true")
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "department", cascade = CascadeType.DETACH, fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<Employee> employees;
}
