package com.THS_GROUP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursDTO {
    private Long id;
    private String nom;
    private String description;
    private Long enseignantId;
}