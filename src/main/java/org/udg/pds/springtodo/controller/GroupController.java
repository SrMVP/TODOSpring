package org.udg.pds.springtodo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.udg.pds.springtodo.controller.exceptions.ControllerException;
import org.udg.pds.springtodo.entity.IdObject;
import org.udg.pds.springtodo.entity.Group;
import org.udg.pds.springtodo.entity.Views;
import org.udg.pds.springtodo.service.GroupService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@RequestMapping(path="/group")
@RestController
public class GroupController extends BaseController {

    @Autowired
    GroupService groupService;

    @PostMapping(consumes = "application/json")
    public IdObject addGroup(HttpSession session, @Valid @RequestBody R_Group group) {

        Long userId = getLoggedUser(session);

        if (group.gName == null) {
            throw new ControllerException("No name supplied");
        }
        if (group.gDescription == null) {
            throw new ControllerException("No description supplied");
        }

        return groupService.addGroup(group.gName,userId, group.gDescription);
    }

    @GetMapping
    public Collection<Group> listAllGroups(HttpSession session) {
        Long userId = getLoggedUser(session);

        return groupService.getGroups(userId);
    }

    static class R_Group {

        @NotNull
        public String gName;

        @NotNull
        public String gDescription;
    }
}