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
public class RoomConcour implements Serializable {

    @EmbeddedId
    private RoomConcourID primaryKey = new RoomConcourID();
    private long candidates = 0;
    private long presence = 0;
    private long absence = 0;
    private long reports = 0;
    private boolean isDone = false;

    @JsonIgnore
    @ManyToOne
    @MapsId("roomID")
    @JoinColumn(name = "roomID")
    Room room;

    @JsonIgnore
    @ManyToOne
    @MapsId("concourID")
    @JoinColumn(name = "concourID")
    Concour concourr;

}
