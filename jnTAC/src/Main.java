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



        System.out.println("1.1: " + firstPlayer.getMumbles().get(0).getCurrentPosition());
        System.out.println("1.2: " + firstPlayer.getMumbles().get(1).getCurrentPosition());
        System.out.println("1.3: " + firstPlayer.getMumbles().get(2).getCurrentPosition());
        System.out.println("1.4: " + firstPlayer.getMumbles().get(3).getCurrentPosition());
        System.out.println("");

        System.out.println("2.1: " + secondPlayer.getMumbles().get(0).getCurrentPosition());
        System.out.println("2.2: " + secondPlayer.getMumbles().get(1).getCurrentPosition());
        System.out.println("2.3: " + secondPlayer.getMumbles().get(2).getCurrentPosition());
        System.out.println("2.4: " + secondPlayer.getMumbles().get(3).getCurrentPosition());
        System.out.println("");


        firstPlayer.getCards().get(3).playSelectedCard(allPlayers, firstPlayer, 0);
        firstPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 0);

        secondPlayer.getCards().get(3).playSelectedCard(allPlayers, firstPlayer, 1);
        secondPlayer.getCards().get(1).playSelectedCard(allPlayers, firstPlayer, 1);

        System.out.println("1.1: " + firstPlayer.getMumbles().get(0).getCurrentPosition());
        System.out.println("1.2: " + firstPlayer.getMumbles().get(1).getCurrentPosition());
        System.out.println("1.3: " + firstPlayer.getMumbles().get(2).getCurrentPosition());
        System.out.println("1.4: " + firstPlayer.getMumbles().get(3).getCurrentPosition());
        System.out.println("");

        System.out.println("2.1: " + secondPlayer.getMumbles().get(0).getCurrentPosition());
        System.out.println("2.2: " + secondPlayer.getMumbles().get(1).getCurrentPosition());
        System.out.println("2.3: " + secondPlayer.getMumbles().get(2).getCurrentPosition());
        System.out.println("2.4: " + secondPlayer.getMumbles().get(3).getCurrentPosition());
        System.out.println("");







    }
}
