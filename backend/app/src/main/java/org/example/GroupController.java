package org.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository, UserGroupRepository userGroupRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
    }

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

    @PostMapping("/{groupId}/join")
    public ReponseEntity<GroupResponse> join(@RequestParam Long groupId, Principal principal) {
        if(groupRepository.findById(groupId).isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new GroupResponse(groupId, null, "group doesn't exist"));
        }
        Group group = groupRepository.findById(groupId).orElseThrow();
        String emailOrUsername = principal.getName();
        User user = userRepository.findByEmail(emailOrUsername)
            .or(() -> userRepository.findByUsername(emailOrUsername))
            .orElseThrow();

        if(userGroupRepository.existsByUserAndGroup(user, group)) {
            return ResponseEntity.badRequest()
                .body(new GroupResponse(groupId, group.getName(), "user is already in group"));
        }

        UserGroup userGroup = new UserGroup(user, group);
        userGroupRepository.save(userGroup);

        return ResponseEntity.ok(new GroupResponse(group.getId(), group.getName(), "joined group"));
    }

    @GetMapping("/my-groups")
    public List<Group> myGroups(Principal principal) {
        String emailOrUsername = principal.getName();
        User user = userRepository.findByEmail(emailOrUsername)
                .or(() -> userRepository.findByUsername(emailOrUsername))
                .orElseThrow();

        List<Group> groups = new ArrayList<>();
        user.getUserGroups().forEach(userGroup -> 
                groups.add(userGroup.getGroup()));

        return groups;
    }

    @DeleteMapping("{groupId}/leave")
    public ResponseEntity<GroupResponse> leave(@RequestParam Long groupId, Principal principal) {
        if(groupRepository.findById(groupId).isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new GroupResponse(null, null, "group doesn't exist"));
        }

        Group group = groupRepository.findById(groupId).orElseThrow();

        String emailOrUsername = principal.getName();
        User user = userRepository.findByEmail(emailOrUsername)
                .or(() -> userRepository.findByUsername(emailOrUsername))
                .orElseThrow();

        if(!userGroupRepository.existsByUserAndGroup(user,group)) {
            return ResponseEntity.badRequest()
                .body(new GroupResponse(group.getId(), group.getName(), "user is not in group"));
        }

        userGroupRepository.delete(
            userGroupRepository.findByUserAndGroup(user, group)
                .orElseThrow());

        return ResponseEntity.ok(new GroupResponse(group.getId(), group.getName(), "left group"));
    }

    // @GetMapping("/{groupId}/leaderboard")
    // public List<LeaderBoardRecord> getLeaderboardStats(@RequestParam Long groupId) {
    //     List<LeaderBoardRecord> groupInfo = new ArrayList<>();
    //     Group group = groupRepository.findById(groupId).orElseThrow();

        
    //     return groupInfo;
    // }
    
}
