import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //----------------------------------------------------------------------------//
        // Create two players
        //----------------------------------------------------------------------------//

        // create two player objects
        Player firstPlayer = new Player(null, 1,"Player ONE");
        Player secondPlayer = new Player(null, 17,"Player TWO");

        firstPlayer.drawCards();
        secondPlayer.drawCards();

        // list of all players
        List<Player> allPlayers = new LinkedList<Player>();

        // add player to the players list
        allPlayers.add(firstPlayer);
        allPlayers.add(secondPlayer);

        //----------------------------------------------------------------------------//
        // TEST: CARDS
        //----------------------------------------------------------------------------//

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


        firstPlayer.getCards().get(3).playSelectedCard(allPlayers, firstPlayer, 2);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 2);

        secondPlayer.getCards().get(3).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);

        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, secondPlayer, 3);
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



        DataStructure<Mumble> test = new DataStructure<>();

        test.add(new Mumble(firstPlayer, 6, PLAYGROUND.START_FIELD, true));
        test.add(new Mumble(firstPlayer, 2, PLAYGROUND.START_FIELD, true));
        test.add(new Mumble(firstPlayer, 45, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + test.get(0).getPosition());
        System.out.println("my own structure: " + test.get(1).getPosition());
        System.out.println("my own structure: " + test.get(2).getPosition());

        test.set(1, new Mumble(firstPlayer, 234, PLAYGROUND.START_FIELD, true));

        System.out.println("my own structure: " + test.get(0).getPosition());
        System.out.println("my own structure: " + test.get(1).getPosition());
        System.out.println("my own structure: " + test.get(2).getPosition());








    }
}
