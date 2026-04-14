package org.example;

public record GroupResponse(Long id, String name, String status) {}
public record CreateGroupRequest(String name){}