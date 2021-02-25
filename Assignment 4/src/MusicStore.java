//Melissa Hawley 260730658

package assignment4;

import java.util.ArrayList;

public class MusicStore {
    private MyHashTable<String, Song> titleToSong;
    private MyHashTable<Integer, ArrayList<Song>> yearToSong;
    private MyHashTable<String, ArrayList<Song>> artistToSong;
    
    public MusicStore(ArrayList<Song> songs) {
    	titleToSong = new MyHashTable<>(songs.size());
    	yearToSong = new MyHashTable<>(songs.size());
    	artistToSong = new MyHashTable<>(songs.size());
    	
    	for (Song s:songs) {
    		addSong(s);  		
    	}
    	
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
		titleToSong.put(s.getTitle(), s);
		ArrayList<Song> sngs = yearToSong.get(s.getYear());
		
		if( sngs == null) {
			sngs = new ArrayList<>();
			yearToSong.put(s.getYear(), sngs);
		}
		sngs.add(s);
		
		sngs = artistToSong.get(s.getArtist());
		if( sngs == null) {
			sngs = new ArrayList<>();
			artistToSong.put(s.getArtist(), sngs);
		}
		sngs.add(s);
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        return titleToSong.get(title);
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        return artistToSong.get(artist);
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        return yearToSong.get(year);
        
    }
}
