package com.example.assignment.dto;

public class IdDTO {
    private Long id;

    public IdDTO(Long id){
        this.id=id;
    }
    public IdDTO(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
