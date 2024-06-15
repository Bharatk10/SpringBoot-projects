package com.zettamine.boot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	private String materialId;
	@Column(name ="mat_desc",nullable = false)
	private String desc;
	@Column(nullable = false)
	private String materialType;
	
	
	@OneToMany(mappedBy="material", cascade= CascadeType.ALL)
	private List<MaterialInspection> matInsp = new ArrayList<>();
	
}
