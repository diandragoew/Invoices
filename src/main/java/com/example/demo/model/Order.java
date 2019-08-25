package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Setter
@ToString
@NoArgsConstructor
@Table(name = "orders")
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "docNo")
	private Long docNo;
	@Column(name = "zaDelivID")
	private long zaDelivID;
	@Column(name = "line")
	private int line;
	@Column(name = "artID")
	private long artID;
	@Column(name = "expDate")
	private LocalDate expDate;
	@Column(name = "serNo")
	private String serNo;
	@Column(name = "qty")
	private float qty;
	@Column(name = "priceVal")
	private double priceVal;
	@Column(name = "totalVal")
	private double totalVal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "invoice_doc_no", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Invoice invoice;
}
