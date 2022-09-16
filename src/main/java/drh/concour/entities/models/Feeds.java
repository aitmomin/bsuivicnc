package drh.concour.entities.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Feeds  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String centerName;
    private String event;
    private Date feedDate;
    private boolean seen = false;

    public Feeds(String centerName, String event, Date feedDate) {
        this.centerName = centerName;
        this.event = event;
        this.feedDate = feedDate;
    }
}
