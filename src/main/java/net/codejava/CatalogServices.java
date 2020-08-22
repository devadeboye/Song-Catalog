package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServices {

    @Autowired
    private SongRepository repo;

    public List<Song> listAll() {
        return repo.findAll();
    }

    public void save(Song song) {
        repo.save(song);
    }

    public Song getSong(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}