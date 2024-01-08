package com.sanjeev.learning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="role_table")
public class Role {
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE)
	Integer id;
	String name;
}
