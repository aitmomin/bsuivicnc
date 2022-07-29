package drh.concour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @OneToMany(mappedBy="concour", fetch= FetchType.LAZY)
    private Set<CenterConcour> centerConcours = new HashSet<>();

    @OneToMany(mappedBy="concourr", fetch= FetchType.LAZY)
    private Set<RoomConcour> roomConcours = new HashSet<>();
}
