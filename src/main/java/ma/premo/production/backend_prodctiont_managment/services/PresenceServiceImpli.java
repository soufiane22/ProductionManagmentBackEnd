package ma.premo.production.backend_prodctiont_managment.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.premo.production.backend_prodctiont_managment.models.Presence;
import ma.premo.production.backend_prodctiont_managment.repositories.PresenceRep;
import ma.premo.production.backend_prodctiont_managment.repositories.ProduitRep;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PresenceServiceImpli implements PresenceService {
    private final PresenceRep presenceRep;

    @Override
    public Presence save(Presence presence) {
        log.info("Saving presence",presence);
        return presenceRep.save(presence);
    }

    @Override
    public Collection<Presence> list(int limit) {
        return  presenceRep.findAll(of(0,limit)).toList();
    }

    @Override
    public Collection<Presence> getALL() {
        log.info("Fetching all presence");
        return presenceRep.findAll();
    }

    @Override
    public Collection<Presence> saveAll(Collection<Presence> listPresence){
        return presenceRep.saveAll(listPresence);
    }

    @Override
    public Collection<Presence> getPresencetByShift(String shift) {
        return null;
    }

    @Override
    public Collection<Presence> getPresenceByDate(String date) {
        return presenceRep.findPresenceByDate(date);
    }

    @Override
    public Collection<Presence> getBetweenTwoDates(Date startDate, Date endDate) {

        return presenceRep.findPresenceByCreatedAtBetween(startDate ,endDate);
    }


    @Override
    public Presence get(String id) {
        log.info("Fetching presence by id ",id);
        return presenceRep.findById(id).orElseThrow();
    }

    @Override
    public Presence update(String id, Presence p) {
        log.info("update presence",p);
        Presence presence = new Presence();
        presence = presenceRep.findById(id).orElseThrow();
        presence.setEtat(p.getEtat());
        presence.setNbrHeurs(p.getNbrHeurs());
        presence.setShift(p.getShift());
        return presenceRep.save(presence);
    }

    @Override
    public Boolean delete(String id) {
        presenceRep.deleteById(id);
        return true;
    }
}
