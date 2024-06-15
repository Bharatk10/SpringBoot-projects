package com.zettamine.rest.entity;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@XmlRootElement
public class Book {

	private Integer bookId;
	private String bookName;
	private Float bookPrice;
}
