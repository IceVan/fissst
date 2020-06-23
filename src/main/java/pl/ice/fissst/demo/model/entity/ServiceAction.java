package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "tsr_service_action")
public class ServiceAction {

    @ApiModelProperty(notes = "Service action ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "Part reference")
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    @ApiModelProperty(notes = "Part name")
    private String name;

    @ApiModelProperty(notes = "Start of service date")
    private Date servicedSince;

    @ApiModelProperty(notes = "End of service date")
    private Date servicedUntil;
}
