package drh.concour.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CenterConcourID implements Serializable {
    /*@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Centre centre;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Concour concour;*/

    @Column(name = "centerID")
    private Long centerID;

    @Column(name = "concourID")
    private Long concourID;
}
