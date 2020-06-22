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
/*@NamedNativeQuery(name = "fetchAllDataForPartInfo",
        query = "SELECT * FROM tpr_part_info pi \n" +
                "INNER JOIN tpr_part p ON p.part_info_id = pi.id \n" +
                "LEFT JOIN tsa_sales_pitch sp ON sp.part_id = p.id \n" +
                "LEFT JOIN tsr_service_action sa ON sa.part_id = sp.part_id\n" +
                "WHERE pi.model_id = :modelId AND pi.name like '%:nameFilter%' AND pi.description like '%:descriptionFilter%' ",
        resultSetMapping = "PartInfoWithAllDataMapping")
@SqlResultSetMapping(name = "PartInfoWithAllDataMapping",
                    entities = {
                        @EntityResult(entityClass = PartInfo.class,
                                        fields = {
                                                @FieldResult(name = "id", column = "pi.id"),
                                                @FieldResult(name = "name", column = "pi.name"),
                                                @FieldResult(name = "description", column = "pi.description"),
                                                @FieldResult(name = "model", column = "pi.model_id")
                                        }),
                            @EntityResult(entityClass = Part.class,
                                    fields = {
                                            @FieldResult(name = "id", column = "p.id"),
                                            @FieldResult(name = "daysToSend", column = "p.days_to_send"),
                                            @FieldResult(name = "inStock", column = "p.in_stock"),
                                            @FieldResult(name = "price", column = "p.price"),
                                            @FieldResult(name = "daysToSend", column = "p.days_to_send"),
                                            @FieldResult(name = "inStock", column = "p.in_stock"),
                                    }),
                            @EntityResult(entityClass = PartInfo.class,
                                    fields = {
                                            @FieldResult(name = "id", column = "pi.id"),
                                            @FieldResult(name = "name", column = "pi.name"),
                                            @FieldResult(name = "description", column = "pi.description"),
                                            @FieldResult(name = "model", column = "pi.model_id")
                                    }),
                            @EntityResult(entityClass = PartInfo.class,
                                    fields = {
                                            @FieldResult(name = "id", column = "pi.id"),
                                            @FieldResult(name = "name", column = "pi.name"),
                                            @FieldResult(name = "description", column = "pi.description"),
                                            @FieldResult(name = "model", column = "pi.model_id")
                                    }),
                    })*/
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
