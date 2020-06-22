package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_info_id", nullable = false)
    private PartInfo partInfo;

    @OneToMany(mappedBy = "part")
    @Fetch(FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ServiceAction> serviceActions = new LinkedList<>();

    @OneToMany(mappedBy = "part")
    @Fetch(FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<SalesPitch> salesPitches = new LinkedList<>();

    private BigDecimal price;

    private boolean inStock;

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
