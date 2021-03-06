package ma.premo.production.backend_prodctiont_managment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Document(collection = "notification_heures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification_Heures  {

    @Id
    @GeneratedValue(strategy = AUTO)
    private String id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "of",referencedColumnName = "id")
    private OF OF;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produit",referencedColumnName = "id")
    private Produit produit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ligne",referencedColumnName = "id")
    private Line ligne;

    private String idLeader;
    private String shift;
    private String date ;
    private String remark;
    private int nbr_operateurs;
    private int total_h;
    private int h_sup;
    private int h_devolution;
    private int h_nouvau_projet;
    private int h_arrete ;
    private int h_normal;

    public Notification_Heures(OF OF,Line l , Produit p, String chef_equipe, String shift, String date, String remark,
                               int nbr_operateurs, int total_h, int h_sup, int h_devolution,
                               int heures_nouvau_projet, int h_arrete , int hNormal) {
        this.OF = OF;
        this.ligne = l;
        this.produit = p;
        this.idLeader = chef_equipe;
        this.shift = shift;
        this.date = date;
        this.nbr_operateurs = nbr_operateurs;
        this.total_h = total_h;
        this.h_sup = h_sup;
        this.h_devolution = h_devolution;
        this.h_nouvau_projet = heures_nouvau_projet;
        this.h_arrete = h_arrete;
        this.remark = remark;
        this.h_normal = hNormal;
    }

    public OF getOF() {
        return OF;
    }

    public void setOF(OF OF) {
        this.OF = OF;
    }


}
