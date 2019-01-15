package com.wissensalt.rnd.sts.shared.data.model;

import com.wissensalt.rnd.sts.shared.data.dto.response.AuthorityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 1/3/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable, UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private static Date CURRENT_DATE = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date")
    private Date expiredDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "credentials_expired_date")
    private Date credentialsExpiredDate;

    @Column(name = "account_non_locked")
    private Boolean nonLocked;

    @Column(name = "login_status")
    private Boolean loginStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "link_user_role",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )}
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        for (Role role : getRoles()) {
            LOGGER.debug(role.getName());
        }
        return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<AuthorityDTO> getAuthorityDTOs() {
        return getRoles().stream().map(role -> new AuthorityDTO(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.getCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getExpiredDate().compareTo(CURRENT_DATE) > 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsExpiredDate().compareTo(CURRENT_DATE) > 0;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }
}
