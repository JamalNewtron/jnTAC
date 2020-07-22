import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //----------------------------------------------------------------------------//
        // Create two players
        //----------------------------------------------------------------------------//

        // Start positions
        //  START1 = 1	    0
        //  START2 = 17	    16
        //  START3 = 33	    32
        //  START4 = 49	    48

        // create two player objects
        Player firstPlayer = new Player(null, 0,"Player ONE");
        Player secondPlayer = new Player(null, 16,"Player TWO");

        firstPlayer.drawCards();
        secondPlayer.drawCards();


        // list of all players
        List<Player> allPlayers = new LinkedList<Player>();

        // add player to the players list
        allPlayers.add(firstPlayer);
        allPlayers.add(secondPlayer);

        // initialize PlayingField
        PlayingField.getPlayingField(allPlayers);

        //----------------------------------------------------------------------------//
        // TEST: CARDS
        //----------------------------------------------------------------------------//
        Scanner in = new Scanner(System.in);
        String userInput = new String("");

        boolean continueGame = true;
        while(continueGame) {

            for(int i = 0; i < allPlayers.size(); i++) {
                for(int n = 0; n < allPlayers.get(i).getCards().size(); n++){
                    System.out.println("Player: "
                            + allPlayers.get(i).getPlayerNumber()
                            + ", card: "
                            + allPlayers.get(i).getCards().get(n).getCardName());
                }
                System.out.println("");
            }

            for(int i = 0; i < allPlayers.size(); i++) {
                for(int n = 0; n < allPlayers.get(i).getMumbles().size(); n++){
                    System.out.println("Player: "
                            + allPlayers.get(i).getPlayerNumber()
                            + ", mumble @ : "
                            + allPlayers.get(i).getMumbles().get(n).getPosition());
                }
                System.out.println("");
            }

            System.out.println("Player1: Which card do you want to play?");
            String useCard = in.nextLine();
            System.out.println("Player1: Which mumble to use?");
            String useMumble = in.nextLine();

            allPlayers.get(0).getCards().get(Integer.parseInt(useCard)).playSelectedCard(allPlayers, allPlayers.get(0), Integer.parseInt(useMumble));




            System.out.println("Do you want to play next round?");
            userInput = in.nextLine();
            if(userInput.equals("y") || userInput.equals("yes")){
                continueGame = true;
            } else {
                continueGame = false;
            }
        }




        firstPlayer.getCards().get(3).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);

        secondPlayer.getCards().get(3).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);


        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);

        //firstPlayer.getCards().get(4).playSelectedCard(allPlayers, firstPlayer, 2);

        //secondPlayer.getCards().get(4).playSelectedCard(allPlayers, secondPlayer, 3);
        //secondPlayer.getCards().get(4).playSelectedCard(allPlayers, secondPlayer, 3);


        System.out.println("1.1: " + firstPlayer.getMumbles().get(0).getPosition());
        System.out.println("1.2: " + firstPlayer.getMumbles().get(1).getPosition());
        System.out.println("1.3: " + firstPlayer.getMumbles().get(2).getPosition());
        System.out.println("1.4: " + firstPlayer.getMumbles().get(3).getPosition());
        System.out.println("");

        System.out.println("2.1: " + secondPlayer.getMumbles().get(0).getPosition());
        System.out.println("2.2: " + secondPlayer.getMumbles().get(1).getPosition());
        System.out.println("2.3: " + secondPlayer.getMumbles().get(2).getPosition());
        System.out.println("2.4: " + secondPlayer.getMumbles().get(3).getPosition());
        System.out.println("");

        for(int i = 0; i < 65; i++) {
            if(PlayingField.getPlayingField().getField(i) != null) {
                System.out.println(PlayingField.getPlayingField().getField(i).getPosition());
            }
        }

        DataStructure<Mumble> asdf = new DataStructure<Mumble>();

        asdf.add(new Mumble(firstPlayer, 6, PLAYGROUND.START_FIELD, true));
        asdf.add(new Mumble(firstPlayer, 2, PLAYGROUND.START_FIELD, true));
        asdf.add(new Mumble(firstPlayer, 45, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + asdf.get(0).getPosition());
        System.out.println("my own structure: " + asdf.get(1).getPosition());
        System.out.println("my own structure: " + asdf.get(2).getPosition());

        asdf.set(1, new Mumble(firstPlayer, 234, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + asdf.get(0).getPosition());
        System.out.println("my own structure: " + asdf.get(1).getPosition());
        System.out.println("my own structure: " + asdf.get(2).getPosition());








    }
}
