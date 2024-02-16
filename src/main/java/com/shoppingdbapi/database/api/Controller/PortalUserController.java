package com.shoppingdbapi.database.api.Controller;

import com.shoppingdbapi.database.api.Model.PortalUser;
import com.shoppingdbapi.database.api.Repository.PortalUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/user")
public class PortalUserController {
    @Autowired
    PortalUserRepo portalUserRepo;

    @PostMapping("/add")
    public PortalUser addUser(@RequestBody PortalUser portalUser){
        portalUserRepo.save(portalUser);
        return portalUser;
    }
}
