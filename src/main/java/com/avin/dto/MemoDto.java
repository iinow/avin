package com.avin.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MemoDto implements Serializable {
	private static final long serialVersionUID = -2570115145141477978L;

	private Long id;
	private String content;
	private Date cdt;
	private Date udt;
}
