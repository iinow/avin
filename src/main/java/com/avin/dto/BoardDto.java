package com.avin.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author BISHOP
 * @since 2019.11.01
 * */

@Getter
@Setter
@EqualsAndHashCode
public abstract class BoardDto {

	@NotNull
	private String title;
	
	@NotNull
	private String content;
	
	private Date cdt;
	
	private Date udt;
}
