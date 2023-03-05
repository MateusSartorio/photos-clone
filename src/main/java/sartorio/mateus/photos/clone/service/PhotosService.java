package sartorio.mateus.photos.clone.service;

import org.springframework.stereotype.Service;
import sartorio.mateus.photos.clone.model.Photo;
import sartorio.mateus.photos.clone.repository.PhotosRepository;

import java.util.UUID;

@Service
public class PhotosService {
    private final PhotosRepository photosRepository;

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public Photo save(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(filename);
        photo.setData(data);
        photosRepository.save(photo);
        return photo;
    }
}
