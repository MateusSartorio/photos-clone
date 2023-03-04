package sartorio.mateus.photos.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.HTML;
import java.util.*;

@RestController
public class PhotosController {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpeg"));
    }};

    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if(photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {
        db.remove(id);
    }

    @PostMapping("/photos")
    public Photo create(@RequestBody @Valid Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }
}
