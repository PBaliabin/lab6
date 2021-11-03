package project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TVDTO {

    private int id;
    private int voltage;
    private int diagonal;

    private String brand;
    private String connectionType;
    private String model;
}
