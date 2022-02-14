package me.sanha.ohmymusicbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Song {

    @Id
    private Long id;

    private String fileName;

    private String title;
    private String artist;
    private boolean isFavorited;
}
