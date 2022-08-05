package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Proxy(lazy=false)
@EqualsAndHashCode
public class Concour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String rank;
    private Date concourDate;

    public Concour(String title, String rank, Date concourDate) {
        this.title = title;
        this.rank = rank;
        this.concourDate = concourDate;
    }

    //@OneToMany(mappedBy="concour", fetch= FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="center", fetch= FetchType.LAZY)
    private List<CenterConcour> centerConcours = new ArrayList<>();

    @OneToMany(mappedBy="room", fetch= FetchType.LAZY)
    private List<RoomConcour> roomConcours = new ArrayList<>();
}
