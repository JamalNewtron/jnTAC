import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // just for testing

        // four mumbles for first player
        Mumble firstPlayerFirstMumble = new Mumble();
        Mumble firstPlayerSecondMumble = new Mumble();
        Mumble firstPlayerThirdMumble = new Mumble();
        Mumble firstPlayerFourthMumble = new Mumble();

        // four stones for second player
        Mumble secondPlayerFirstMumble = new Mumble();
        Mumble secondPlayerSecondMumble = new Mumble();
        Mumble secondPlayerThirdMumble = new Mumble();
        Mumble secondPlayerFourthMumble = new Mumble();

        // initialize two lists
        List<Mumble> firstPlayerMumbles = new LinkedList<Mumble>();
        List<Mumble> secondPlayerMumbles = new LinkedList<Mumble>();;

        // add mumbles to list
        firstPlayerMumbles.add(firstPlayerFirstMumble);
        firstPlayerMumbles.add(firstPlayerSecondMumble);
        firstPlayerMumbles.add(firstPlayerThirdMumble);
        firstPlayerMumbles.add(firstPlayerFourthMumble);

        // add mumbles to list
        secondPlayerMumbles.add(secondPlayerFirstMumble);
        secondPlayerMumbles.add(secondPlayerSecondMumble);
        secondPlayerMumbles.add(secondPlayerThirdMumble);
        secondPlayerMumbles.add(secondPlayerFourthMumble);

        //----------------------------------------------------------------------------//

        // four cards for first player
        Card firstPlayerFirstCard = new NumberedCard(9);
        Card firstPlayerSecondCard = new NumberedCard(12);
        Card firstPlayerThirdCard = new NumberedSpecialCard(13);
        Card firstPlayerFourthCard = new NumberedSpecialCard(4);

        // four cards for second player
        Card secondPlayerFirstCard = new NumberedCard(9);
        Card secondPlayerSecondCard = new NumberedCard(12);
        Card secondPlayerThirdCard = new NumberedSpecialCard(1);
        Card secondPlayerFourthCard = new NumberedSpecialCard(4);

        // initialize two lists
        List<Card> firstPlayerCards = new LinkedList<Card>();
        List<Card> secondPlayerCards = new LinkedList<Card>();;

        // add mumbles to list
        firstPlayerCards.add(firstPlayerFirstCard);
        firstPlayerCards.add(firstPlayerSecondCard);
        firstPlayerCards.add(firstPlayerThirdCard);
        firstPlayerCards.add(firstPlayerFourthCard);


        // add mumbles to list
        secondPlayerCards.add(secondPlayerFirstCard);
        secondPlayerCards.add(secondPlayerSecondCard);
        secondPlayerCards.add(secondPlayerThirdCard);
        secondPlayerCards.add(secondPlayerFourthCard);

        //----------------------------------------------------------------------------//
        // TEST: MUMBLES
        //----------------------------------------------------------------------------//

        // create two player objects
        Player firstPlayer = new Player(firstPlayerMumbles, firstPlayerCards, 1,"Player ONE");
        Player secondPlayer = new Player(secondPlayerMumbles, secondPlayerCards, 17,"Player TWO");

        // list of all players
        List<Player> allPlayers = new LinkedList<Player>();

        // add player to the players list
        allPlayers.add(firstPlayer);
        allPlayers.add(secondPlayer);


//        int positionBefore = firsPlayer.getMumbles().get(0).getIntPosition();
//        System.out.println("position before: " + positionBefore);
//        firsPlayer.getMumbles().get(0).checkPositionForOccupation(allPlayers, firsPlayer, PLAYGROUND.START_FIELD, 3);
//
//        int changedPosition = firsPlayer.getMumbles().get(0).getIntPosition();
//        System.out.println("position changed to: " + changedPosition);


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

        ((NumberedSpecialCard)firstPlayer.getCards().get(2)).bringMumbleIntoPlay(allPlayers, firstPlayer, 1);
        ((NumberedSpecialCard)secondPlayer.getCards().get(2)).bringMumbleIntoPlay(allPlayers, secondPlayer, 2);


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
