package drh.concour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Center implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String jury;
    private String address;
    private String city;
    private String region;
    private Date plannedOpening;
    private Date plannedClosing;
    private Date dateConcour;

    // fields for steps in frontend
    @Column(columnDefinition = "bit default 0")
    private boolean isReady = false;
    private Date readyAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isOpened = false;
    private Date openedAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isClosed = false;
    private Date closedAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isReadyDistributed = false;
    private Date readyDistributedAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isStartDistributed = false;
    private Date startDistributedAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isEndDistributed = false;
    private Date endDistributedAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isExamEnd = false;
    private Date examEndAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isEnd = false;
    private Date endAt;
    @Column(columnDefinition = "bit default 0")
    private boolean isDelivered = false;
    private Date deliveredAt;
    @Column(columnDefinition = "varchar(50) default 'step1'")
    private String step = "step1";

    // fields for statistics
    private long candidates;
    @Column(columnDefinition = "bigint default 0")
    private long presence = 0;
    @Column(columnDefinition = "bigint default 0")
    private long absence = 0;
    @Column(columnDefinition = "bigint default 0")
    private long reports = 0;


    public Center(String jury, String address, String city, Date plannedOpening, Date plannedClosing) {
        this.jury = jury;
        this.address = address;
        this.city = city;
        this.plannedOpening = plannedOpening;
        this.plannedClosing = plannedClosing;
        this.isReady = false;
        this.readyAt = null;
        this.isClosed = false;
        this.closedAt = null;
        this.isOpened = false;
        this.openedAt = null;
        this.isReadyDistributed = false;
        this.readyDistributedAt = null;
        this.isStartDistributed = false;
        this.startDistributedAt = null;
        this.isEndDistributed = false;
        this.endDistributedAt = null;
        this.isExamEnd = false;
        this.examEndAt = null;
        this.isEnd = false;
        this.endAt = null;
        this.isDelivered = false;
        this.deliveredAt = null;
        this.candidates = 0;
        this.presence = 0;
        this.absence = 0;
        this.reports = 0;
    }

    public Center(String jury, String address, String city, String region, Date plannedOpening, Date plannedClosing) {
        this.jury = jury;
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
        this.isReadyDistributed = false;
        this.readyDistributedAt = null;
        this.isStartDistributed = false;
        this.startDistributedAt = null;
        this.isEndDistributed = false;
        this.endDistributedAt = null;
        this.isExamEnd = false;
        this.examEndAt = null;
        this.isEnd = false;
        this.endAt = null;
        this.isDelivered = false;
        this.deliveredAt = null;
        this.candidates = 0;
        this.presence = 0;
        this.absence = 0;
        this.reports = 0;
    }

    //@OneToMany(mappedBy= "center", fetch= FetchType.LAZY)
    //@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();


    //@OneToMany(mappedBy= "center", fetch= FetchType.EAGER)
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
    @OneToMany(mappedBy= "concour", fetch= FetchType.EAGER)
    private List<CenterConcour> centerConcours = new ArrayList<>();

}
