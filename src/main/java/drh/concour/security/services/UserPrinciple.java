package drh.concour.security.services;

import drh.concour.entities.Center;
import drh.concour.entities.User;
import drh.concour.entities.Remark;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

    private long doti;
    private String lastname;
    private String firstname;
    private String tel;
    private String city;
    private String mail;
    private String identifier;
    private String password;
    private String rank;
    private String degree;
    private String cin;
    private String direction;
    private String code;
    private int jury;
    private boolean isResponsible;
    private boolean isBlocked;
    private Center center;
    private List<Remark> remarks;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(long doti, String lastname, String firstname, String tel, String city, String mail, String identifier, String password, String rank, String degree, String cin, String direction, /*String code, int jury,*/ boolean isResponsible, boolean isBlocked, Center center, List<Remark> remarks, Collection<? extends GrantedAuthority> authorities) {
        this.doti = doti;
        this.lastname = lastname;
        this.firstname = firstname;
        this.tel = tel;
        this.city = city;
        this.mail = mail;
        this.identifier = identifier;
        this.password = password;
        this.rank = rank;
        this.degree = degree;
        this.cin = cin;
        this.direction = direction;
        // this.code = code;
        // this.jury = jury;
        this.isResponsible = isResponsible;
        this.isBlocked = isBlocked;
        this.center = center;
        this.remarks = remarks;
        this.authorities = authorities;
    }

    public long getDoti() {
        return doti;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getTel() {
        return tel;
    }

    public String getCity() {
        return city;
    }

    public String getMail() {
        return mail;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getRank() {
        return rank;
    }

    public String getDegree() {
        return degree;
    }

    public String getCin() {
        return cin;
    }

    public String getDirection() {
        return direction;
    }

    public String getCode() {
        return code;
    }

    public int getJury() {
        return jury;
    }

    public boolean isResponsible() {
        return isResponsible;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public static UserPrinciple build(User p) {
        List<GrantedAuthority> authorities = p.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                p.getDoti(),
                p.getLastname(),
                p.getFirstname(),
                p.getTel(),
                p.getCity(),
                p.getMail(),
                p.getUsername(),
                p.getPassword(),
                p.getRank(),
                p.getDegree(),
                p.getCin(),
                p.getDirection(),
                // p.getCode(),
                // p.getJury(),
                p.isResponsible(),
                p.isBlocked(),
                p.getCenter(),
                p.getRemarks(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return this.identifier;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(doti, user.doti);
    }
}