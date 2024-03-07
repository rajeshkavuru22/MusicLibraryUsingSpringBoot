package com.example.song;

import com.example.song.SongRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// Don't modify the below code
public class SongService implements SongRepository {
    private static HashMap<Integer, Song> playlist = new HashMap<>();
    int uniqSongId = 6;

    public SongService() {
        playlist.put(1, new Song(1, "Butta Bomma", "Ramajogayya Sastry", "Armaan Malik", "Thaman S"));
        playlist.put(2, new Song(2, "Kathari Poovazhagi", "Vijay", "Benny Dayal, Swetha Mohan", "A.R. Rahman"));
        playlist.put(3, new Song(3, "Tum Hi Ho", "Mithoon", "Arijit Singh", "Mithoon"));
        playlist.put(4, new Song(4, "Vizhiyil", "Vairamuthu", "Unni Menon", "A.R. Rahman"));
        playlist.put(5, new Song(5, "Nenjame", "Panchu Arunachalam", "S.P.Balasubrahmanyam", "Ilaiyaraaja"));
    }

    // Don't modify the above code

    @Override
    public ArrayList<Song> getSongs() {
        Collection<Song> songCollection = playlist.values();
        ArrayList<Song> songs = new ArrayList<>(songCollection);
        return songs;
    }

    @Override
    public Song getSong(int songId) {
        if (playlist.get(songId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return playlist.get(songId);
    }

    @Override
    public Song addSong(Song song) {
        song.setSongId(uniqSongId);
        playlist.put(uniqSongId, song);
        uniqSongId += 1;
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        Song existingSong = playlist.get(songId);

        if (existingSong == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (song.getSongName() != null) {
            existingSong.setSongName(song.getSongName());
        }

        if (song.getSinger() != null) {
            existingSong.setSinger(song.getSinger());
        }

        if (song.getLyricist() != null) {
            existingSong.setLyricist(song.getLyricist());
        }

        if (song.getMusicDirector() != null) {
            existingSong.setMusicDirector(song.getMusicDirector());
        }

        return existingSong;
    }

    @Override
    public void deleteSong(int songId) {
        Song song = playlist.get(songId);
        if (song == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            playlist.remove(song);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}