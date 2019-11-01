package com.avin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tags")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Tag extends BaseEntity {
	private static final long serialVersionUID = -2521190317817782767L;

	@Id
	@Column(name = "TAG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	public Tag(String name) {
		this.name = name;
	}
	
	public Tag() {
	}
}
