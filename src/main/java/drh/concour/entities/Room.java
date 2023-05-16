package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    // private String number;
    private String name;
    @Column(name = "`from`")
    private long from;
    @Column(name = "`to`")
    private long to;

    // additionnal fields for assignment
    private String center;
    private String description;
    private String jury;

    // stats fields
    private long candidates;
    @Column(columnDefinition = "bigint default 0")
    private long presence = 0;
    @Column(columnDefinition = "bigint default 0")
    private long absence = 0;
    @Column(columnDefinition = "bigint default 0")
    private long reports = 0;
    @Column(columnDefinition = "bit default 0")
    private boolean isDone = false;
    @Column(columnDefinition = "bit default 0")
    private boolean isAffected = false;

    public Room(String name, long candidates) {
        // this.number = number;
        this.name = name;
        this.candidates = candidates;
    }

    public Room(String n, String name, long candidates, long presence, long absence, CenterConcour centerConcour) {
        // this.number = number;
        this.name = name;
        this.candidates = candidates;
        this.presence = presence;
        this.absence = absence;
        this.centerConcour = centerConcour;
    }


    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="concourID", referencedColumnName="concourid"),
        @JoinColumn(name="centerID", referencedColumnName="centerid")
    })
    private CenterConcour centerConcour;



    // old version of mapping
    /*@JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "concourID", referencedColumnName = "id")
    private Concour concour;*/


}
