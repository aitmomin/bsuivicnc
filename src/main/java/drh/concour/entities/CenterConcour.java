package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CenterConcour implements Serializable {

    @EmbeddedId
    private CenterConcourID primaryKey = new CenterConcourID();

    @JsonIgnore
    @ManyToOne
    @MapsId("centerID")
    @JoinColumn(name = "centerID")
    private Center center;

    @JsonIgnore
    @ManyToOne
    @MapsId("concourID")
    @JoinColumn(name = "concourID")
    private Concour concour;

}
