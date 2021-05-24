package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "content_info")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "content_id")
	private int contentId;

	@Column(name = "content_title", nullable = false)
	private String contentTitle;

	@Column(name = "content_writer", nullable = false)
	private String contentWriter;

	@Column(name = "content_content", nullable = false)
	private String contentContent;

	@Column(name = "written_date", nullable = false)
	private String writtenDate;

	public int getContentId() {
		return contentId;
	};

	public void setContentId(int contentId) {
		this.contentId = contentId;
	};

	public String getContentTitle() {
		return contentTitle;
	};

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	};

	public String getContentWriter() {
		return contentWriter;
	};

	public void setContentWriter(String contentWriter) {
		this.contentWriter = contentWriter;
	};

	public String getContentContent() {
		return contentContent;
	};

	public void setContentContent(String contentContent) {
		this.contentContent = contentContent;
	};

	public String getWrittenDate() {
		return writtenDate;
	};

	public void setWrittenDate(String writtenDate) {
		this.writtenDate = writtenDate;
	};

	@Override
	public String toString() {
		return "Content(id: " + this.contentId + ", title: " + this.contentTitle + ", writer: " + this.contentWriter
				+ ", content: " + this.contentContent + ", writtendDate: " + this.writtenDate + ")";
	};
}
