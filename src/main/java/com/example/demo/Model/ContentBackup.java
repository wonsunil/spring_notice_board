package com.example.demo.Model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "content_backup")
public class ContentBackup {
    @Id
    @SequenceGenerator( name = "backup_content_seq", sequenceName = "backup_content_seq", allocationSize = 1 )
    @GeneratedValue(generator = "backup_content_seq")
    private int deletedId;

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

    @Column(name = "deleted_date", nullable = false)
    private String deletedDate;

    @Override
    public String toString() {
        return "Content(id: " + this.contentId + ", title: " + this.contentTitle + ", writer: " + this.contentWriter
                + ", content: " + this.contentContent + ", writtenDate: " + this.writtenDate + ", deletedDate: " + this.deletedDate + ")";
    }

    ;
}
