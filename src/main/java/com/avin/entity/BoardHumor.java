package com.avin.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avin.common.util.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board_humors")
@DiscriminatorValue(value = Constants.BOARD_HUMOR)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "humor_id"))
public class BoardHumor extends Board {
	private static final long serialVersionUID = 5265576857147609746L;

}
