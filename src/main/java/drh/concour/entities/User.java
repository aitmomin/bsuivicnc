package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "`user`")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(long doti, String lastname, String firstname, String tel, String city, String mail, String identifier, String password, String rank, String degree,
                String cin, String direction, String code) {
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
        this.code = code;
        this.isBlocked = false;
        this.isResponsible = false;
    }

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "centerID", referencedColumnName = "id")
    private Center center;

    @OneToMany(mappedBy= "user", fetch= FetchType.LAZY)
    private List<Remark> remarks = new ArrayList<Remark>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            joinColumns= @JoinColumn(name="userID"),
            inverseJoinColumns=@JoinColumn(name="roleID")
    )
    private Set<Role> roles = new HashSet<>();

}
