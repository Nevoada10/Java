package videoGamesUNS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Library.java
 * Class representing a video game library
 * Manages collection of video games and provides search functionality
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Library {

//============================
// ATTRIBUTES
//============================

    private String name;
    private List<VideoGame> videoGames;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a library with specified name
     * @param name Library name
     */
    public Library(String name) {
        this.name = name;
        this.videoGames = new ArrayList<>();
    }

//============================
// METHODS
//============================

    /**
     * Returns video games that contain the specified genre
     * @param genre Genre to search for
     * @return List of video games with that genre
     */
    public List<VideoGame> videoGamesByGenre(Genre genre) {
        List<VideoGame> result = new ArrayList<>();
        
        for (VideoGame game : videoGames) {
            if (game.getGenres().contains(genre)) {
                result.add(game);
            }
        }
        
        return result;
    }

    /**
     * Returns video games that contain the text in creator name (case-insensitive)
     * @param text Text to search in creator names
     * @return List of video games with matching creators
     */
    public List<VideoGame> videoGamesByCreator(String text) {
        List<VideoGame> result = new ArrayList<>();
        
        for (VideoGame game : videoGames) {
            for (Creator creator : game.getCreators()) {
                if (creator.getName().toLowerCase().contains(text.toLowerCase())) {
                    result.add(game);
                    break; // Don't add same game multiple times
                }
            }
        }
        
        return result;
    }

    /**
     * Returns video games that contain the text in platform name (case-insensitive)
     * @param text Text to search in platform names
     * @return List of video games with matching platforms
     */
    public List<VideoGame> videoGamesByPlatform(String text) {
        List<VideoGame> result = new ArrayList<>();
        
        for (VideoGame game : videoGames) {
            for (Platform platform : game.getPlatforms()) {
                if (platform.getName().toLowerCase().contains(text.toLowerCase())) {
                    result.add(game);
                    break; // Don't add same game multiple times
                }
            }
        }
        
        return result;
    }

    /**
     * Returns list of creators born between specified years (no duplicates)
     * @param from Start year (inclusive)
     * @param to End year (inclusive)
     * @return List of creators without duplicates
     */
    public List<Creator> listAllCreatorsByYear(int from, int to) {
        Set<Creator> uniqueCreators = new HashSet<>();
        
        for (VideoGame game : videoGames) {
            for (Creator creator : game.getCreators()) {
                // Extract year from birthDate (assumes format YYYY-MM-DD or YYYY)
                String birthDate = creator.getBirthDate();
                int birthYear = Integer.parseInt(birthDate.substring(0, 4));
                
                if (birthYear >= from && birthYear <= to) {
                    uniqueCreators.add(creator);
                }
            }
        }
        
        return new ArrayList<>(uniqueCreators);
    }

    /**
     * Returns total price of video games with specified rating
     * Note: Price attribute not in VideoGame class, returning 0
     * @param rating Rating to filter by
     * @return Total price
     */
    public float getTotalPrice(Rating rating) {
        // Note: VideoGame doesn't have price attribute in the diagram
        // This method would need a price field to work properly
        // Returning 0 as placeholder
        return 0.0f;
    }

    /**
     * Returns video games that have both the specified genre and platform
     * @param genre Genre to search for
     * @param platform Platform to search for
     * @return List of video games matching both criteria
     */
    public List<VideoGame> videoGamesByGenresAndPlatforms(Genre genre, Platform platform) {
        List<VideoGame> result = new ArrayList<>();
        
        for (VideoGame game : videoGames) {
            boolean hasGenre = game.getGenres().contains(genre);
            boolean hasPlatform = game.getPlatforms().contains(platform);
            
            if (hasGenre && hasPlatform) {
                result.add(game);
            }
        }
        
        return result;
    }

    /**
     * Returns a string representation of the library
     * @return Formatted string with library information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Library: ").append(name).append("\n");
        result.append("Video Games:\n");
        
        for (VideoGame game : videoGames) {
            result.append("  - ").append(game.getTitle()).append("\n");
        }
        
        return result.toString();
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the library name
     * @return Library name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the library name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the video games list
     * @return List of video games
     */
    public List<VideoGame> getVideoGames() {
        return videoGames;
    }

    /**
     * Sets the video games list
     * @param videoGames Video games to set
     */
    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }
}