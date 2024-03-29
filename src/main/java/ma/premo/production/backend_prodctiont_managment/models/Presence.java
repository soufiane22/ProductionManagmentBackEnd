package ma.premo.production.backend_prodctiont_managment.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Document(collection = "presence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Presence {
    @Id
    @GeneratedValue(strategy = AUTO)
    private String id;
    private String idPerson;
    private String nomPerson;
    private String prenomPerson;
    private int matriculePerson;
    private String functionPerson;
    private String etat;
    private String shift;
    private Double nbrHeurs = 0.0;
    private Line line = null;
    private User leader;
    private User technicalExpert;
    private User supervisor;
    private String zone;
    private String date;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdAt;


}
