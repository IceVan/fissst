package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity(name = "tsa_sales_pitch")
public class SalesPitch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;
}
