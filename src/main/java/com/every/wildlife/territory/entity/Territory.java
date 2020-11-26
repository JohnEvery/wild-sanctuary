package com.every.wildlife.territory.entity;

import com.every.wildlife.territory.controller.dto.GroupDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Territory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String territoryName;

    private String territoryCode;

    private Double square;

    private String responsiblePersonName;

    private String responsiblePersonPhone;

    @Transient
    private List<GroupDTO> groupDTOS;

    public Territory(String territoryName, String territoryCode, Double square,
                     String responsiblePersonName, String responsiblePersonPhone, List<GroupDTO> groupDTOS) {
        this.territoryName = territoryName;
        this.territoryCode = territoryCode;
        this.square = square;
        this.responsiblePersonName = responsiblePersonName;
        this.responsiblePersonPhone = responsiblePersonPhone;
        this.groupDTOS = groupDTOS;
    }
}
