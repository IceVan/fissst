package pl.ice.fissst.demo.model.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "tbr_brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Model> models = new LinkedList<>();

    public void addModel(Model model) {
        models.add(model);
        model.setBrand(this);
    }

    public void removeModel(Model model) {
        model.setBrand(null);
        this.models.remove(model);
    }
}
