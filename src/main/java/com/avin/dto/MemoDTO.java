package com.avin.dto;

import java.io.Serializable;

import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Setter
@EqualsAndHashCode
public class MemoDTO implements Serializable {
	private static final long serialVersionUID = -2570115145141477978L;

	private String content;
}
