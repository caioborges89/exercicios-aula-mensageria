package com.facef.kafka.contapalvras.dto;

import java.io.Serializable;
import java.util.Date;


public class WordCount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	private Long count;
	private Date start;
	private Date end;

	public WordCount() {

	}

	public WordCount(String key, Long count, Date start, Date end) {
		super();
		this.key = key;
		this.count = count;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "WordCount [key=" + key + ", count=" + count + ", start=" + start + ", end=" + end + "]";
	}

	// getters and setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}