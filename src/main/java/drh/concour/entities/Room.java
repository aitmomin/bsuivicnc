package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String name;
    private long candidates;
    private long presence = 0;
    private long absence = 0;
    private long reports = 0;
    private boolean isDone = false;

    public Room(String number, String name, long candidates) {
        this.number = number;
        this.name = name;
        this.candidates = candidates;
    }

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "concourID", referencedColumnName = "id")
    private Concour concour;


    /*@OneToMany(mappedBy= "concour", fetch= FetchType.LAZY)
    private List<RoomConcour> roomConcours = new ArrayList<>();*/





    /*@JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "centerID", insertable = false, updatable = false),
            @JoinColumn(name = "concourID", insertable = false, updatable = false)
    })
    private CenterConcour centerRoom;*/


}
