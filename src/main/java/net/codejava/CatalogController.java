package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CatalogController {

    @Autowired
    private CatalogServices service;

    @GetMapping("/songs")
    public List<Song> list() {
        return service.listAll();
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> get(@PathVariable Integer id) {
        try {
            Song song = service.getSong(id);
            return new ResponseEntity<Song>(song, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/songs")
    public void add(@RequestBody Song song) {
        service.save(song);
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<?> update(@RequestBody Song song, @PathVariable Integer id) {
        try {
            Song validSong = service.getSong(id);
            service.save(song);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // return 404 if resource is not available
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Song validSong = service.getSong(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // return 404 if resource is not available
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}