package com.hb.picom.pojos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Advertisment {
	private int id;
	private String title;
	private boolean isActive;
	private String description;
	private String urlImage;
	private String htmlText;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime creationDate = LocalDateTime.now();
	private List<Area> areas = new ArrayList<Area>();
	private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getHtmlText() {
		return htmlText;
	}
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public List<Area> getAreas() {
		return areas;
	}
	
	public void addArea(Area area) {
		areas.add(area);
	}
	
	public List<TimeSlot> getTimeSlot() {
		return timeSlots;
	}
	
	public void addTimeSlot(TimeSlot timeSlot) {
		timeSlots.add(timeSlot);
	}
	
	public Advertisment() {
		
	}
	
	public Advertisment(int id, String title, String description, String urlImage) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.urlImage = urlImage;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		sb.append("title: "+title+",\n");
		
		if(isActive) {
			sb.append("is active: yes,\n");
		}else {
			sb.append("is active: no,\n");
		}
		
		sb.append("description:\n"+description+",\n");
		sb.append("image url:\n "+urlImage+",\n");
		
		if(htmlText != null) {
			sb.append("HTML text:\n"+htmlText+",\n");
		}
		
		if(startDate != null) {
			sb.append("start date:\n"+FORMATTER.format(startDate)+",\n");
		}
		
		if(endDate != null) {
			sb.append("end date:\n"+FORMATTER.format(endDate)+",\n");
		}
		
		sb.append("creation date:\n"+FORMATTER.format(creationDate));
	
		return sb.toString();
	}
	
	public void showAreas() {
		for (Area area : areas) {
			System.out.println(area);
		}
		
	}
	
	public void showTimeSlots() {
		for (TimeSlot timeSlot : timeSlots) {
			System.out.println(timeSlot);
		}
		
	}
	
}
