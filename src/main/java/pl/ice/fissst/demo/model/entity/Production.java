package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "tbr_model_production")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    private Date producedSince;

    private Date producedUntil;
}
