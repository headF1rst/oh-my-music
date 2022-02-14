package me.sanha.ohmymusicbackend.repository;

import me.sanha.ohmymusicbackend.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
}
