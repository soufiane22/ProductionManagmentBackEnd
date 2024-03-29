package ma.premo.production.backend_prodctiont_managment.controler;

import lombok.RequiredArgsConstructor;
import ma.premo.production.backend_prodctiont_managment.models.Notification_Heures;
import ma.premo.production.backend_prodctiont_managment.models.Presence;
import ma.premo.production.backend_prodctiont_managment.models.Produit;
import ma.premo.production.backend_prodctiont_managment.models.Response;
import ma.premo.production.backend_prodctiont_managment.services.PresenceService;
import ma.premo.production.backend_prodctiont_managment.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/presence")

public class PresenceController {
    @Autowired
    private final PresenceService presenceService;


    @PostMapping("/save")
    public ResponseEntity<Response> savePresence(@RequestBody Presence p){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Presence",presenceService.save(p)))
                        .message("Presence craeted")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    // save list of presences


        @PostMapping("/saveAll")
        public Collection<Presence> saveAllPresences(@RequestBody Collection<Presence> listPresence){
            return presenceService.saveAll(listPresence);
        }



    //get all presences

    @GetMapping("/getAll")
    public    ResponseEntity<Response>  getAllPresences() {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data1(presenceService.getALL())
                        .message("get all Presences")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }



    // get Products par id

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getPresencetById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Presence", presenceService.get(id)))
                        .message("presence retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/getbetween/{startdate}/{enddate}")
    public Collection<Presence> getPresenceBetweenDates(@PathVariable("startdate") String startDate , @PathVariable("enddate") String endtDate ) throws ParseException {
        Date dateS =new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date dateE =new SimpleDateFormat("yyyy-MM-dd").parse(endtDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateE);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date modifiedDate = cal.getTime();

        return  presenceService.getBetweenTwoDates(dateS,modifiedDate);
    }

    @GetMapping("/getbydate/{date}")
    public Collection<Presence> getNotificationsByDate(@PathVariable("date") String date) {
        return  presenceService.getPresenceByDate(date);
    }


    @PutMapping("/update/{id}")
    // @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Response> UpdatePresence(@PathVariable("id") String id ,@RequestBody Presence presence) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Presence updated",presenceService.update(id , presence)))
                        .message("Presence is updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeletePresence(@PathVariable("id") String id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Presence deleted",presenceService.delete(id)))
                        .message("Presence is deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}


