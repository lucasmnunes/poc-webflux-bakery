package poc.webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Item {

    @Id
    private String id;

    private String nome;

    private String description;

    private BigDecimal value;
}
