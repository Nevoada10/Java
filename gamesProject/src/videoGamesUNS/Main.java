package videoGamesUNS;

import java.util.List;

/**
 * Main.java
 * Test class for Video Games Library system
 * Tests all classes and search functionality
 * @Author: Uriel Neves Silva (https://gitlab.com/a253119un)
 */
public class Main {
    public static void main(String[] args) {
        
        //============================
        // CREATE LIBRARY
        //============================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            CREATING VIDEO GAMES LIBRARY                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        Library library = new Library("My Video Game Library");
        System.out.println("► Library created: " + library.getName() + "\n");
        
        //============================
        // CREATE PLATFORMS
        //============================
        System.out.println("► Creating platforms...\n");
        Platform ps5 = new Platform("PlayStation5", "Sony", "2020");
        Platform nintendoSwitch = new Platform("Nintendo Switch", "Nintendo", "2017");
        Platform xbox = new Platform("Xbox Series X", "Microsoft", "2020");
        Platform pc = new Platform("PC", "Various", "N/A");
        
        //============================
        // CREATE CREATORS
        //============================
        System.out.println("► Creating creators...\n");
        Creator hironobu = new Creator("Hironobu Sakaguchi", "1962-11-25", "Japan");
        Creator shigeru = new Creator("Shigeru Miyamoto", "1952-11-16", "Japan");
        Creator takashi = new Creator("Takashi Tezuka", "1960-11-17", "Japan");
        Creator cdProjekt = new Creator("CD Projekt RED Team", "1994-01-01", "Poland");
        
        //============================
        // CREATE VIDEO GAMES
        //============================
        System.out.println("► Creating video games...\n");
        
        // Final Fantasy
        VideoGame finalFantasy = new VideoGame("Final Fantasy", 2020, Rating.P16, true);
        finalFantasy.addGenre(Genre.RPG);
        finalFantasy.addGenre(Genre.ADVENTURE);
        finalFantasy.addPlatform(ps5);
        finalFantasy.addPlatform(nintendoSwitch);
        finalFantasy.addCreator(hironobu);
        library.getVideoGames().add(finalFantasy);
        
        // The Legend of Zelda
        VideoGame zelda = new VideoGame("The Legend of Zelda", 2017, Rating.P12, false);
        zelda.addGenre(Genre.ADVENTURE);
        zelda.addGenre(Genre.ACTION);
        zelda.addPlatform(nintendoSwitch);
        zelda.addCreator(shigeru);
        zelda.addCreator(takashi);
        library.getVideoGames().add(zelda);
        
        // Super Mario Bros
        VideoGame mario = new VideoGame("Super Mario Bross", 2021, Rating.P7, true);
        mario.addGenre(Genre.PLATFORMS);
        mario.addGenre(Genre.ACTION);
        mario.addPlatform(nintendoSwitch);
        mario.addCreator(shigeru);
        library.getVideoGames().add(mario);
        
        // The Witcher
        VideoGame witcher = new VideoGame("The Witcher", 2015, Rating.P18, false);
        witcher.addGenre(Genre.RPG);
        witcher.addGenre(Genre.ADVENTURE);
        witcher.addPlatform(ps5);
        witcher.addPlatform(xbox);
        witcher.addPlatform(pc);
        witcher.addCreator(cdProjekt);
        library.getVideoGames().add(witcher);
        
        System.out.println("✓ Video games added to library\n");
        
        //============================
        // TEST: videoGamesByGenre
        //============================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               TEST: videoGamesByGenre(RPG)                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        List<VideoGame> rpgGames = library.videoGamesByGenre(Genre.RPG);
        System.out.println("► Video games with RPG genre:");
        for (VideoGame game : rpgGames) {
            System.out.println("  - " + game.getTitle());
        }
        
        //============================
        // TEST: videoGamesByCreator
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           TEST: videoGamesByCreator(\"shigeru\")               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        List<VideoGame> shiguruGames = library.videoGamesByCreator("shigeru");
        System.out.println("► Video games by creator containing 'shigeru':");
        for (VideoGame game : shiguruGames) {
            System.out.println("  - " + game.getTitle());
        }
        
        //============================
        // TEST: videoGamesByPlatform
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          TEST: videoGamesByPlatform(\"Nintendo\")              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        List<VideoGame> nintendoGames = library.videoGamesByPlatform("Nintendo");
        System.out.println("► Video games on Nintendo platforms:");
        for (VideoGame game : nintendoGames) {
            System.out.println("  - " + game.getTitle());
        }
        
        //============================
        // TEST: listAllCreatorsByYear
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        TEST: listAllCreatorsByYear(1960, 1980)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        List<Creator> creatorsInRange = library.listAllCreatorsByYear(1960, 1980);
        System.out.println("► Creators born between 1960 and 1980:");
        for (Creator creator : creatorsInRange) {
            System.out.println("  - " + creator.getName());
        }
        
        //============================
        // TEST: getTotalPrice
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║             TEST: getTotalPrice(P18)                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        float totalPrice = library.getTotalPrice(Rating.P18);
        System.out.println("► Total price of P18 games: " + totalPrice);
        System.out.println("  Note: Price attribute not implemented in VideoGame class");
        
        //============================
        // TEST: videoGamesByGenresAndPlatforms
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   TEST: videoGamesByGenresAndPlatforms(RPG, PlayStation5)    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        List<VideoGame> rpgPs5Games = library.videoGamesByGenresAndPlatforms(Genre.RPG, ps5);
        System.out.println("► Video games with RPG genre on PlayStation5:");
        for (VideoGame game : rpgPs5Games) {
            System.out.println("  - " + game.getTitle());
        }
        
        //============================
        // SHOW LIBRARY
        //============================
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FULL LIBRARY                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        System.out.println(library);
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   ALL TESTS COMPLETED!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}