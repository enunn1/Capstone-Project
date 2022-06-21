package org.ethannunn.gamereviews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="console")
@Data
@NoArgsConstructor
public class ConsoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int console_id;

	@Column(name="console_name")
	private String consoleName;

	public ConsoleEntity(String consoleName) {
		this.consoleName = consoleName;
	}
}
