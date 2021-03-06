package ma.premo.production.backend_prodctiont_managment.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.premo.production.backend_prodctiont_managment.models.Groupe;
import ma.premo.production.backend_prodctiont_managment.models.Presence;
import ma.premo.production.backend_prodctiont_managment.models.Response;
import ma.premo.production.backend_prodctiont_managment.repositories.GroupeRep;
import ma.premo.production.backend_prodctiont_managment.services.GroupeService;
import ma.premo.production.backend_prodctiont_managment.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groupe")

public class GroupeController {
    @Autowired
    private final GroupeService groupeService;


    @GetMapping(value="/")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String hello(){
        return "Hello World!!";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    @Secured(value={"ROLE_ADMIN"})
    public ResponseEntity<Response> saveGroup(@RequestBody Groupe g){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Groupe",groupeService.save(g)))
                        .message("Groupe craeted")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    //get All groups
    @CrossOrigin(origins = "*")
    @GetMapping("/getAll")
    public    ResponseEntity<Response>  getAllGroups() {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data1(groupeService.getALL())
                        .message("get all Presences")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    // get Products par id
    @CrossOrigin(origins = "*")
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getGroupById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Group", groupeService.get(id)))
                        .message("group retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    // get Products par Designation
    @CrossOrigin(origins = "*")
    @GetMapping("/get/designation/{designation}")
    public ResponseEntity<Response> getGroupByDesignation(@PathVariable("designation") String designation) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .object(groupeService.getByDesignation(designation))
                        .message("group retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    // get Products par Designation
    @CrossOrigin(origins = "*")
    @GetMapping("/get/leader/{leader}")
    @Secured(value={"ROLE_ADMIN"})
    public ResponseEntity<Response> getGroupByChefEquipe(@PathVariable("leader") String leader) throws JsonProcessingException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
       // String jsonString = mapper.writeValueAsString(groupeService.getByChefEquipe(leader));
       // JSONObject jsonObject = new JSONObject(jsonString);
        //System.out.println("groupeJson=====>"+jsonString);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .object(groupeService.getByChefEquipe(leader))
                        .message("group retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    //@CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    // @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Response> UpdateGroup(@PathVariable("id") String id ,@RequestBody Groupe groupe) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Group updated",groupeService.update(id , groupe)))
                        .message("Group is updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeleteGroup(@PathVariable("id") String id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("Group deleted",groupeService.delete(id)))
                        .message("Group is deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
