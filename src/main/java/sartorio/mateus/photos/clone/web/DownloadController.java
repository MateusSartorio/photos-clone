package sartorio.mateus.photos.clone.web;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sartorio.mateus.photos.clone.model.Photo;
import sartorio.mateus.photos.clone.service.PhotosService;

@RestController
public class DownloadController {
    private PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) {
        Photo photo = photosService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();

        headers.setContentDisposition(build);

        return new ResponseEntity<>(photosService.get(id).getData(), headers, HttpStatus.OK);
    }
}
