package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "tbr_model")
public class Model {

    @ApiModelProperty(notes = "Model ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "Brand reference")
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ApiModelProperty(notes = "Model name")
    @Column(unique = true)
    private String name;

    @ApiModelProperty(notes = "List of production dates")
    @OneToMany(mappedBy = "model")
    @Fetch(FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Production> productionDates;

    @ApiModelProperty(notes = "List of part types")
    @OneToMany(mappedBy = "model")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PartInfo> partTypes = new LinkedList<>();

    public void addPartInfo(PartInfo partInfo) {
        partTypes.add(partInfo);
        partInfo.setModel(this);
    }

    public void removePartInfo(PartInfo partInfo) {
        partInfo.setModel(null);
        this.partTypes.remove(partInfo);
    }

    public void addProduction(Production production) {
        productionDates.add(production);
        production.setModel(this);
    }

    public void removeProduction(Production production) {
        production.setModel(null);
        this.productionDates.remove(production);
    }
}
