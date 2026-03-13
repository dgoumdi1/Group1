package org.example;//this declares the package for this class

import org.springframework.http.ResponseEntity;//this imports response entity for http responses
import org.springframework.web.bind.annotation.*;//this imports spring web annotations

import java.security.Principal;//this imports principal for authenticated user

@RestController//this marks the class as a rest controller
@RequestMapping("/api/groups")//this sets the base path for group endpoints
public class GroupController {//this starts the group controller class

    private final GroupRepository groupRepository;//this stores the group repository
    private final UserRepository userRepository;//this stores the user repository

    public GroupController(GroupRepository groupRepository, UserRepository userRepository) {//this is the constructor
        this.groupRepository = groupRepository;//this assigns the group repository
        this.userRepository = userRepository;//this assigns the user repository
    }//this ends the constructor

    @PostMapping//this maps http post to create group
    public ResponseEntity<GroupResponse> create(@RequestBody CreateGroupRequest request, Principal principal) {//this handles create group
        if (groupRepository.findByName(request.name()).isPresent()) {//this checks for existing group name
            return ResponseEntity.badRequest()//this returns a bad request response
                    .body(new GroupResponse(null, request.name(), "Group name already exists"));//this returns a response body
        }//this ends the duplicate check

        String emailOrUsername = principal.getName();//this gets the authenticated name
        User owner = userRepository.findByEmail(emailOrUsername)//this looks up by email
                .or(() -> userRepository.findByUsername(emailOrUsername))//this falls back to username
                .orElseThrow();//this fails if no user is found
        Group group = new Group(request.name());//this creates a new group
        group.setOwner(owner);//this sets the group owner
        groupRepository.save(group);//this saves the group

        return ResponseEntity.ok(new GroupResponse(group.getId(), group.getName(), "created"));//this returns success response
    }//this ends the create method
}//this ends the controller class

record CreateGroupRequest(String name) {}//this defines the create request record
record GroupResponse(Long id, String name, String status) {}//this defines the response record