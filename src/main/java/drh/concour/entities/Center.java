package drh.concour.entities;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Center implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String city;
    private String region;
    private Date plannedOpening;
    private Date plannedClosing;
    private boolean isReady;
    private Date readyAt;
    private boolean isClosed;
    private Date closedAt;
    private boolean isOpened;
    private Date openedAt;
    private boolean isDistributed;
    private Date distributedAt;
    private boolean isEnd;
    private Date endAt;
    private long candidates;
    private long presence;
    private long absence;
    private long reports;

    public Center(String name, String address, String city, String region, Date plannedOpening, Date plannedClosing) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.plannedOpening = plannedOpening;
        this.plannedClosing = plannedClosing;
        this.isReady = false;
        this.readyAt = null;
        this.isClosed = false;
        this.closedAt = null;
        this.isOpened = false;
        this.openedAt = null;
        this.isDistributed = false;
        this.distributedAt = null;
        this.isEnd = false;
        this.endAt = null;
        this.candidates = 0;
        this.presence = 0;
        this.absence = 0;
        this.reports = 0;
    }

    @OneToMany(mappedBy= "center", fetch= FetchType.EAGER)
    private Set<User> commission = new HashSet<User>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy= "center", fetch= FetchType.EAGER)
    private Set<CenterConcour> centerConcours = new HashSet<>();

}
