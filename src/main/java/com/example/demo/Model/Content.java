package com.example.demo.Model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.VO.IpVO;
import org.hibernate.annotations.ColumnDefault;

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

	@Column(name = "view_count", nullable = false)
	private Integer viewCount;

	@ElementCollection
	private List<IpVO> ipArray = new ArrayList<>();

	@PrePersist
	private void prePersist() {
		viewCount = 0;
	};

	@Override
	public String toString() {
		return "Content(id: " + this.contentId + ", title: " + this.contentTitle + ", writer: " + this.contentWriter
				+ ", content: " + this.contentContent + ", writtenDate: " + this.writtenDate + ")";
	};
}
