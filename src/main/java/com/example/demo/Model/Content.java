package com.example.demo.Model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "content_info")
public class Content {
	@Id
	@SequenceGenerator( name = "content_seq", sequenceName = "content_seq", allocationSize = 1 )
	@GeneratedValue(generator = "content_seq")
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

	@Override
	public String toString() {
		return "Content(id: " + this.contentId + ", title: " + this.contentTitle + ", writer: " + this.contentWriter
				+ ", content: " + this.contentContent + ", writtendDate: " + this.writtenDate + ")";
	};
}
