package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String name;

    public Room(String number, String name) {
        this.number = number;
        this.name = name;
    }

    /*@JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "centerID", insertable = false, updatable = false),
            @JoinColumn(name = "concourID", insertable = false, updatable = false)
    })
    private CenterConcour centerRoom;*/

    @OneToMany(mappedBy= "room", fetch= FetchType.LAZY)
    private List<RoomConcour> roomConcours = new ArrayList<>();
}
