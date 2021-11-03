package project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class TV {
    @Id
    @SequenceGenerator(
            name = "tv_sequence",
            sequenceName = "tv_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tv_sequence"
    )
    private int id;

    private int voltage;
    private int diagonal;

    @Column(nullable = false, columnDefinition = "text")
    private String brand;
    @Column(nullable = false, columnDefinition = "text")
    private String connectionType;
    @Column(nullable = false, columnDefinition = "text")
    private String model;

    public TV() {
        this.voltage = 220;
        this.diagonal = 100;
        this.brand = "brand";
        this.connectionType = "connectionType";
        this.model = "model";
    }

    public TV(int voltage, int diagonal, String brand, String connectionType, String model) {
        this.voltage = voltage;
        this.diagonal = diagonal;
        this.brand = brand;
        this.connectionType = connectionType;
        this.model = model;
    }
}
