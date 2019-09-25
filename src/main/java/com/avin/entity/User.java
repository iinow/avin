package com.avin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.avin.common.util.Constants.PROVIDER;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", 
	uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")},
	indexes = {
		@Index(columnList = "id"), 
		@Index(columnList = "email")}
)
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class User extends BaseEntity {
	private static final long serialVersionUID = -7739731076410946291L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false)
	@Email
	private String email;
	
	@Column(name = "password", nullable = false)
	@NotNull
	private String password;
	
	private String name;
	private String imageUrl;
	private String providerId;
	
	@NotNull
    @Enumerated(EnumType.ORDINAL)
	private PROVIDER provider;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date cdt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date udt;
}
