package com.avin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
//@MappedSuperclass
public abstract class Board extends BaseEntity{
	private static final long serialVersionUID = 4765912110472785117L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private String content;

	@ManyToMany
	@JoinTable(name = "BOARD_TAG", 
		joinColumns = @JoinColumn(name = "id"), 
		inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
	@JsonManagedReference
	private List<Tag> tags = new ArrayList<>();
	
	//출처
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	@NotNull
	private User registerUser;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date cdt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date udt;
}
