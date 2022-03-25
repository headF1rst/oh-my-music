package me.sanha.ohmymusicbackend.controller;

import lombok.RequiredArgsConstructor;
import me.sanha.ohmymusicbackend.model.Song;
import me.sanha.ohmymusicbackend.repository.SongRepository;
import me.sanha.ohmymusicbackend.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SongController {

    private final StorageService storageService;
    private final SongRepository songRepository;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable String id) {

        Optional<Song> song = songRepository.findById(id);

        if (song.isPresent()) {
            return ResponseEntity.ok(song.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createSong(@RequestParam("song") Song song,
                                           @RequestParam("file")MultipartFile file) throws IOException {
        // 이미 등록된 음악 파일이 존재할 때
        if (songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(song.getTitle())) {
            return ResponseEntity.badRequest().body("taken");
        } else {
            System.out.println("파일 업로드 중입니다...");
            storageService.uploadSong(file);

            song.setFileName(file.getOriginalFilename());
            Song insertedSong = songRepository.insert(song);
            return new ResponseEntity<>(insertedSong, HttpStatus.ACCEPTED);
        }
    }
}
