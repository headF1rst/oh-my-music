package me.sanha.ohmymusicbackend;

import lombok.RequiredArgsConstructor;
import me.sanha.ohmymusicbackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OhMyMusicBackendApplication {

    public static void main(String[] args) {

        ApplicationContext ac = SpringApplication.run(OhMyMusicBackendApplication.class, args);

        StorageService storageService = ac.getBean(StorageService.class);
        storageService.getSongFileNames();
    }

}
