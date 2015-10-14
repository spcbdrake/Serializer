import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by benjamindrake on 10/14/15.
 */
public class Serializer {
    static Game game;

    public static void main(String[] args) throws Exception {
        game = loadGame();
        Scanner scanner = new Scanner(System.in);
        if (game == null) {
            game = new Game();
            System.out.println("What is the name of your game?");
            game.name = scanner.nextLine();

            System.out.println("What genre of your game?");
            game.genre = scanner.nextLine();

            System.out.println("What platform does your game run on?");
            game.platform = scanner.nextLine();

            System.out.println("What was the rating of your game on a scale of 1-10");
            game.rating = Integer.valueOf(scanner.nextLine());

            System.out.println("What is the price of your game");
            game.price = Double.valueOf(scanner.nextLine());
        } else {

            System.out.println(String.format("name: %s,genre :%s, platform: %s, rating: %d, price: %f", game.name, game.genre, game.platform, game.rating, game.price));
            System.out.println("Would you like to update your game? [y/n]");
            String s = scanner.nextLine().toLowerCase();
            if (s.equals("y")) {
                System.out.println("What would you like to change?");
                String c = scanner.nextLine().toLowerCase();
                System.out.println("What is the new value ?");
                String newVal = scanner.nextLine().toLowerCase();
                switch(c){
                    case "name":
                        game.name = newVal;
                        break;
                    case "genre":
                        game.genre = newVal;
                        break;
                    case "platform":
                        game.platform = newVal;
                        break;
                    case "rating":
                        game.rating = Integer.valueOf(newVal);
                        break;
                    case "price":
                        game.price = Double.valueOf(newVal);
                        break;
                }

            } else {
                System.exit(0);
            }
        }
        saveGame();

    }

    public static void saveGame() {
        File f = new File("save.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(game);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {
        }
    }

    public static Game loadGame() {
        try {
            File f = new File("save.json");
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents);
            String fileContents = new String(contents);
            JsonParser parser = new JsonParser();
            return parser.parse(fileContents, Game.class);
        } catch (Exception e) {
            return null;
        }
    }

}

