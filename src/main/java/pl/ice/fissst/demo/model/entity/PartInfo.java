package pl.ice.fissst.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "tpr_part_info")
public class PartInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;


    @OneToMany(mappedBy = "partInfo")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Part> parts = new LinkedList<>();

    public void addPart(Part part) {
        parts.add(part);
        part.setPartInfo(this);
    }

    public void removePart(Part part) {
        part.setPartInfo(null);
        this.parts.remove(part);
    }
}
