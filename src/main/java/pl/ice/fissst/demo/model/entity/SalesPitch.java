package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity(name = "tsa_sales_pitch")
public class SalesPitch {

    @ApiModelProperty(notes = "Sales pitch ID")
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

    @ApiModelProperty(notes = "Part description")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;
}
