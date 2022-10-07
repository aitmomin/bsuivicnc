package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

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
@EqualsAndHashCode
//@Proxy(lazy=false)
public class CenterConcour implements Serializable {

    @EmbeddedId
    private CenterConcourID primaryKey = new CenterConcourID();

    public CenterConcour(Center center, Concour concour) {
        this.center = center;
        this.concour = concour;
    }

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

    // new version of mapping
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "centerConcour", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<Room>();

}
