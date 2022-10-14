package drh.concour.entities.models;

import lombok.Data;

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
    private String title;
    private String rank;
    private String name;
    private long candidates;
    private long presence = 0;
    private long absence = 0;
}
