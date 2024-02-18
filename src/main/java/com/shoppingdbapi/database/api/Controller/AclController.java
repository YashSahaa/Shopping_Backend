package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.DTO.Requestbody.AddAccessDTO;
import com.shoppingdbapi.database.api.DTO.Responsebody.AclConfigDTO;
import com.shoppingdbapi.database.api.Model.Acl;
import com.shoppingdbapi.database.api.Repository.AclRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db/acl")
public class AclController {
    @Autowired
    AclRepo aclRepo;

    @PostMapping("/add")
    public void registerAccess(@RequestBody AddAccessDTO addAccessDTO){
        Acl acl = new Acl();
        acl.setOperation(addAccessDTO.getOperator());
        acl.setRequestor(addAccessDTO.getRequestor());
        aclRepo.save(acl);
    }
    @GetMapping("/validate")
    public ResponseEntity isAccessAvailable(@RequestParam String requestor, @RequestParam String operation){
        Acl acl = aclRepo.getConfiguration(requestor,operation);
        if(acl!=null) return new ResponseEntity<>(new AclConfigDTO(true), HttpStatus.OK);
        else return new ResponseEntity<>(new AclConfigDTO(false), HttpStatus.OK);
    }
}
