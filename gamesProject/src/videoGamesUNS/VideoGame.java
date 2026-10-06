package videoGamesUNS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * VideoGame.java
 * Class representing a video game
 * Contains title, genres, platforms, creators, release year, rating and multiplayer
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class VideoGame {

//============================
// ATTRIBUTES
//============================

    private String title;
    private List<Genre> genres;
    private Set<Platform> platforms;
    private Set<Creator> creators;
    private int releaseYear;
    private Rating rating;
    private boolean multiplayer;

//============================
// CONSTRUCTOR
//============================

    /**
     * Constructor
     * Creates a video game with title, release year, rating and multiplayer
     * Initializes empty collections for genres, platforms and creators
     * @param title Video game title
     * @param releaseYear Release year
     * @param rating PEGI rating
     * @param multiplayer Whether game supports multiplayer
     */
    public VideoGame(String title, int releaseYear, Rating rating, boolean multiplayer) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.multiplayer = multiplayer;
        this.genres = new ArrayList<>();
        this.platforms = new HashSet<>();
        this.creators = new HashSet<>();
    }

//============================
// METHODS
//============================

    /**
     * Adds a genre to the video game
     * @param genre Genre to add
     * @return true if added successfully, false otherwise
     */
    public boolean addGenre(Genre genre) {
        if (genre != null && !genres.contains(genre)) {
            return genres.add(genre);
        }
        return false;
    }

    /**
     * Adds a platform to the video game
     * @param platform Platform to add
     * @return true if added successfully, false otherwise
     */
    public boolean addPlatform(Platform platform) {
        if (platform != null) {
            return platforms.add(platform);
        }
        return false;
    }

    /**
     * Adds a creator to the video game
     * @param creator Creator to add
     * @return true if added successfully, false otherwise
     */
    public boolean addCreator(Creator creator) {
        if (creator != null) {
            return creators.add(creator);
        }
        return false;
    }

    /**
     * Returns a string representation of the video game
     * @return Formatted string with video game information
     */
    @Override
    public String toString() {
        return "VideoGame{" +
               "title='" + title + '\'' +
               ", genres=" + genres +
               ", platforms=" + platforms +
               ", creators=" + creators +
               ", releaseYear=" + releaseYear +
               ", rating=" + rating +
               ", multiplayer=" + multiplayer +
               '}';
    }

//============================
// GETTERS AND SETTERS
//============================

    /**
     * Gets the title
     * @return Video game title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     * @param title Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the genres list
     * @return List of genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets the genres list
     * @param genres Genres to set
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets the platforms set
     * @return Set of platforms
     */
    public Set<Platform> getPlatforms() {
        return platforms;
    }

    /**
     * Sets the platforms set
     * @param platforms Platforms to set
     */
    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    /**
     * Gets the creators set
     * @return Set of creators
     */
    public Set<Creator> getCreators() {
        return creators;
    }

    /**
     * Sets the creators set
     * @param creators Creators to set
     */
    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    /**
     * Gets the release year
     * @return Release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year
     * @param releaseYear Release year to set
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the rating
     * @return PEGI rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the rating
     * @param rating Rating to set
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * Checks if multiplayer is supported
     * @return true if multiplayer, false otherwise
     */
    public boolean isMultiplayer() {
        return multiplayer;
    }

    /**
     * Sets multiplayer support
     * @param multiplayer Multiplayer to set
     */
    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }
}