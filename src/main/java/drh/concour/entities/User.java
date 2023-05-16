package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "`user`")
public class User implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long doti;
    private String lastname;
    private String firstname;
    private String lastname_ar;
    private String firstname_ar;
    private String fullName;
    private String tel;
    private String city;
    // @Column(unique=true)
    // test this mail ?
    private String mail;
    @Column(unique=true)
    private String username;
    private String password;
    private String newPassword;
    private String rank;
    private String degree;
    private String rank_degree;
    private String cin;
    private String direction;
    private String address;
    private String gender;
    private String position;
    @Column(columnDefinition = "bit default 0")
    private boolean isResponsible = false;
    @Column(columnDefinition = "bit default 1")
    private boolean isBlocked = true;
    @Column(columnDefinition = "bit default 0")
    private boolean isAffected = false;


    public User(long doti, String lastname_ar, String firstname_ar, String lastname, String firstname, String tel, String city, String mail, String username, String password, String rank, String degree,
                String cin, String direction, String rank_degree, String fullName, String position) {
        this.doti = doti;
        this.lastname = lastname;
        this.firstname = firstname;
        this.lastname_ar = lastname_ar;
        this.firstname_ar = firstname_ar;
        this.fullName = fullName;
        this.position = position;
        this.tel = tel;
        this.city = city;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.degree = degree;
        this.cin = cin;
        this.direction = direction;
        this.rank_degree = rank_degree;
        this.isBlocked = false;
        this.isResponsible = false;
    }

    public User(long doti, String lastname, String firstname, String tel, String city, String mail, String username, String password, String rank, String degree,
                String cin, String direction, String rank_degree) {
        this.doti = doti;
        this.lastname = lastname;
        this.firstname = firstname;
        this.tel = tel;
        this.city = city;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.degree = degree;
        this.cin = cin;
        this.direction = direction;
        this.rank_degree = rank_degree;
        this.isBlocked = false;
        this.isResponsible = false;
    }

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "centerID", referencedColumnName = "id")
    private Center center;

    @JsonIgnore
    @OneToMany(mappedBy= "user", fetch= FetchType.LAZY)
    private List<Remark> remarks = new ArrayList<Remark>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            joinColumns= @JoinColumn(name="userID"),
            inverseJoinColumns=@JoinColumn(name="roleID")
    )
    private List<Role> roles = new ArrayList<>();


}
