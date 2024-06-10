package org.neeq.is_projekt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1500)
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "published_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime publishedAt;

    @Column(name = "content", length = 3000)
    private String content;

}