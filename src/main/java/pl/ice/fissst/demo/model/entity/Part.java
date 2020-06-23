package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "tpr_part")
public class Part {

    @ApiModelProperty(notes = "Part ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "Part type")
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_info_id", nullable = false)
    private PartInfo partInfo;

    @ApiModelProperty(notes = "List of service actions")
    @OneToMany(mappedBy = "part")
    @Fetch(FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ServiceAction> serviceActions = new LinkedList<>();

    @ApiModelProperty(notes = "List of sales pitches")
    @OneToMany(mappedBy = "part")
    @Fetch(FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<SalesPitch> salesPitches = new LinkedList<>();

    @ApiModelProperty(notes = "Price of a part")
    private BigDecimal price;

    @ApiModelProperty(notes = "Is part in stock")
    private boolean inStock;

    @ApiModelProperty(notes = "How much time takes to send part")
    private int daysToSend;

    public void addServiceAction(ServiceAction serviceAction) {
        serviceActions.add(serviceAction);
        serviceAction.setPart(this);
    }

    public void removeServiceAction(ServiceAction serviceAction) {
        serviceAction.setPart(null);
        this.serviceActions.remove(serviceAction);
    }

    public void addSalesPitch(SalesPitch salesPitch) {
        salesPitches.add(salesPitch);
        salesPitch.setPart(this);
    }

    public void removeSalesPitch(SalesPitch salesPitch) {
        salesPitch.setPart(null);
        this.salesPitches.remove(salesPitch);
    }
}
