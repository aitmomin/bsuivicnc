package drh.concour.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoomConcourID implements Serializable {


    @Column(name = "roomID")
    Long roomID;

    @Column(name = "concourID")
    Long concourID;

}
