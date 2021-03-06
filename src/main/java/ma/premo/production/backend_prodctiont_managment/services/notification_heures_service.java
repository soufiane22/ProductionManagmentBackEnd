package ma.premo.production.backend_prodctiont_managment.services;

import ma.premo.production.backend_prodctiont_managment.models.Notification_Heures;


import java.util.Collection;


public interface  notification_heures_service {
    Notification_Heures save(Notification_Heures notif ,String idLine, String idProduit ,String idOf);

    Collection<Notification_Heures> list(int limit);

    Collection<Notification_Heures> getALL();

    Notification_Heures get(String id);

    Notification_Heures update(Notification_Heures produit,String idNotif ,String idLine , String idProduct, String idOf);

    Boolean delete(String id);

    Collection<Notification_Heures> getNotif_heurByHE(String chef_equipe);
}
