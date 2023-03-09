package drh.concour.entities.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class ConcourWithRooms implements Serializable {
    @Id
    private long id;
    private long concourid;
    private String city;
    private String jury;
    private String rank;
    private String title;
    private String name;
    private long candidates;
    @Column(name = "`from`")
    private long from;
    @Column(name = "`to`")
    private long to;
    private long presence = 0;
    private long absence = 0;
}
