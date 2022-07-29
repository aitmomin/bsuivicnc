package drh.concour.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CenterConcourID implements Serializable {
    /*@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Centre centre;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Concour concour;*/

    @Column(name = "centerID")
    Long centerID;

    @Column(name = "concourID")
    Long concourID;
}
