package sartorio.mateus.photos.clone.repository;

import org.springframework.data.repository.CrudRepository;
import sartorio.mateus.photos.clone.model.Photo;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
