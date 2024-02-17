package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.DTO.Requestbody.AddAccessDTO;
import com.shoppingdbapi.database.api.Model.Acl;
import com.shoppingdbapi.database.api.Repository.AclRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
