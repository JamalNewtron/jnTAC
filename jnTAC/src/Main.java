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
                            + allPlayers.get(i).getMumbles().get(n).getNode().index);
                }
                System.out.println("");
            }

            for(int i = 0; i < PlayingField.getPlayingField().getField().size(); i++) {
                System.out.print("prev(" + i + "): " + PlayingField.getPlayingField().getField().getNode(i).prev + " ");
                System.out.print("node(" + i + "): " + PlayingField.getPlayingField().getField().getNode(i) + " ");
                System.out.print("next(" + i + "): " + PlayingField.getPlayingField().getField().getNode(i).next + " ");
                System.out.print("branch(" + i + "): " + PlayingField.getPlayingField().getField().getNode(i).branch + " ");
                System.out.println("");
            }

            for(int i = 0; i < PlayingField.getPlayingField().getField().size(); i++) {

                for (int n = 0; n < allPlayers.size(); n++) {
                    if (PlayingField.getPlayingField().getField().getNode(i).player == allPlayers.get(n)) {
                        System.out.print("node.data(" + i + "): " + PlayingField.getPlayingField().getFieldElement(i));
                        for (int z = 0; z < allPlayers.get(n).getHomeField().size(); z++){
                            System.out.print("    ");
                            System.out.print("home(" + z + "): " + allPlayers.get(n).getHomeField().get(z));
                        }
                        System.out.println("");
                    }
                }

                System.out.println("node.data(" + i + "): " + PlayingField.getPlayingField().getFieldElement(i));
            }

            int player = 0;
            System.out.println("Player1: CARD?");
            String useCard = in.nextLine();
            System.out.println("Player1: MUMBEL?");
            String useMumble = in.nextLine();

            allPlayers.get(player).getCards().get(Integer.parseInt(useCard)).playSelectedCard(allPlayers, allPlayers.get(player), Integer.parseInt(useMumble));

            System.out.println("Player2: CARD?");
            String useCard1 = in.nextLine();
            System.out.println("Player2: MUMBEL?");
            String useMumble1 = in.nextLine();
            player = 1;

            allPlayers.get(player).getCards().get(Integer.parseInt(useCard1)).playSelectedCard(allPlayers, allPlayers.get(player), Integer.parseInt(useMumble1));

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


        System.out.println("1.1: " + firstPlayer.getMumbles().get(0).getNode().index);
        System.out.println("1.2: " + firstPlayer.getMumbles().get(1).getNode().index);
        System.out.println("1.3: " + firstPlayer.getMumbles().get(2).getNode().index);
        System.out.println("1.4: " + firstPlayer.getMumbles().get(3).getNode().index);
        System.out.println("");

        System.out.println("2.1: " + secondPlayer.getMumbles().get(0).getNode().index);
        System.out.println("2.2: " + secondPlayer.getMumbles().get(1).getNode().index);
        System.out.println("2.3: " + secondPlayer.getMumbles().get(2).getNode().index);
        System.out.println("2.4: " + secondPlayer.getMumbles().get(3).getNode().index);
        System.out.println("");

        for(int i = 0; i < 65; i++) {
            if(PlayingField.getPlayingField().getFieldElement(i) != null) {
                System.out.println(PlayingField.getPlayingField().getFieldElement(i).getNode().index);
            }
        }

        DataStructure<Mumble> asdf = new DataStructure<Mumble>();

        asdf.add(new Mumble(firstPlayer, 6, PLAYGROUND.START_FIELD, true));
        asdf.add(new Mumble(firstPlayer, 2, PLAYGROUND.START_FIELD, true));
        asdf.add(new Mumble(firstPlayer, 45, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + asdf.get(0).getNode().index);
        System.out.println("my own structure: " + asdf.get(1).getNode().index);
        System.out.println("my own structure: " + asdf.get(2).getNode().index);

        asdf.set(1, new Mumble(firstPlayer, 234, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + asdf.get(0).getNode().index);
        System.out.println("my own structure: " + asdf.get(1).getNode().index);
        System.out.println("my own structure: " + asdf.get(2).getNode().index);
    }
}
