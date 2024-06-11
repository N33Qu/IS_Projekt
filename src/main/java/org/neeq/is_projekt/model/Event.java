package org.neeq.is_projekt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
    @NotBlank(message = "Event ID cannot be blank")
    @Size(max = 50, message = "Event ID cannot be longer than 50 characters")
    private String id;

    @Column(name = "author")
    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author cannot be longer than 100 characters")
    private String author;

    @Column(name = "title")
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 200, message = "Title cannot be longer than 200 characters")
    private String title;

    @Column(name = "description", length = 1500)
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1500, message = "Description cannot be longer than 1500 characters")
    private String description;

    @Column(name = "url")
    @Size(max = 255, message = "URL cannot be longer than 255 characters")
    private String url;

    @Column(name = "published_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime publishedAt;

    @Column(name = "content", length = 3000)
    @NotBlank(message = "Content cannot be blank")
    @Size(max = 3000, message = "Content cannot be longer than 3000 characters")
    private String content;
}