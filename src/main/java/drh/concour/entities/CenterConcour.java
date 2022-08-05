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
