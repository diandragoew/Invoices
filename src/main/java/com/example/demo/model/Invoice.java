package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "invoices")
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "deliverer_id")
	private int delivererId;
	@Column(name = "proform")
	private boolean proform;
	@Column(name = "total_val")
	private Long totalVal;
	@Column(name = "note")
	private String note;
	@Column(name = "date_padej")
	private LocalDate datePadej;
	@Column(name = "pay_noteid")
	private int payNoteID;
	@Column(name = "pay_type_name")
	private String PayTypeName;
	@Column(name = "version")
	private int version;

	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
}