package org.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    @GetMapping("/{groupId}/leaderboard")
    public List<Map<String,Object>> getLeaderboardStats(@RequestParam String groupId) {
        List<Map<String,Object>> groupUserInfo = new ArrayList<>();
        if()
        return groupUserInfo;
    }
    
}
