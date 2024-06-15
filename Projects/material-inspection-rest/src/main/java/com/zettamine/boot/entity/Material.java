package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zettamine.boot.constants.ValidationConstants;
import com.zettamine.boot.models.ValidMaterialType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "mat_desc"))
public class Material {
    
    @Id
    //@Pattern(regexp = ValidationConstants.MATERIAL_ID_ERROR,message = ValidationConstants.MATERIAL_ID_ERROR)
    private String materialId;
    
    @Column(name ="mat_desc",nullable = false)
    @NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
    private String desc;
    
    @NotBlank(message = ValidationConstants.BLANK_ERROR_MESSAGE)
    @Column(nullable = false)
    @ValidMaterialType
    private String materialType;
    
    @JsonIgnore
    private Character Status;
    
    
    @OneToMany(mappedBy="material", cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<MaterialInspection> matInsp = new ArrayList<>();
    
    @JsonIgnore
    public List<MaterialInspection> getMatInsp() {
        return matInsp.isEmpty() ? null : matInsp;
    }
}
