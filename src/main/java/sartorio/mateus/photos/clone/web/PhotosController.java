package sartorio.mateus.photos.clone.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sartorio.mateus.photos.clone.model.Photo;
import sartorio.mateus.photos.clone.service.PhotosService;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {
    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable Integer id) {
        Photo photo = photosService.get(id);
        if(photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}