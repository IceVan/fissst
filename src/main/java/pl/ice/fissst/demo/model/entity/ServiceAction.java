package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "tsr_service_action")
public class ServiceAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    private String name;

    private Date servicedSince;

    private Date servicedUntil;
}
