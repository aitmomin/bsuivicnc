package drh.concour.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoomConcourID implements Serializable {


    @Column(name = "roomID")
    private Long roomID;

    @Column(name = "concourID")
    private Long concourID;

}
