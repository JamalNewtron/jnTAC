import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Mumble> mumbles;
    private List<Card> cards;
    private int startPosition;
    private String playerName;
    private int playerNumber;
    private static int playerNumberCounter = 1;

    public Player(final List<Card> cards,
                  final int startPosition,
                  final String playerName) {
        this(null, cards, startPosition, playerName);

        // four mumbles
        Mumble firstMumble = new Mumble();
        Mumble secondMumble = new Mumble();
        Mumble thirdMumble = new Mumble();
        Mumble fourthMumble = new Mumble();

        // four stones for second player
        List<Mumble> playerMumbles = new LinkedList<Mumble>();

        // add mumbles to list
        playerMumbles.add(firstMumble);
        playerMumbles.add(secondMumble);
        playerMumbles.add(thirdMumble);
        playerMumbles.add(fourthMumble);

        this.mumbles = playerMumbles;

    }

    public Player(final List<Mumble> mumbles,
                  final List<Card> cards,
                  final int startPosition,
                  final String playerName) {


        this.mumbles = mumbles;
        this.cards = cards;
        this.startPosition = startPosition;
        this.playerName = playerName;
        this.playerNumber = playerNumberCounter;
        playerNumberCounter += 1;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerNumber(final int intPlayerNumber){
        this.playerNumber = intPlayerNumber;
    }

    public List<Mumble> getMumbles() {
        return this.mumbles;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public int getStartPosition() {
        return this.startPosition;
    }


    public void drawCards() {

        // todo: later this needs to be done automatically!
        // five cards

        // initialize list for player cards
        List<Card> playerCards = new LinkedList<Card>();

        // add cards to list
        playerCards.add(new NumberedCard(AVAILABLE_CARD_NUMBERS.TWO));
        playerCards.add(new NumberedCard(AVAILABLE_CARD_NUMBERS.THREE));
        playerCards.add(new NumberedCard(AVAILABLE_CARD_NUMBERS.FIVE));
        playerCards.add(new NumberedSpecialCard(AVAILABLE_CARD_NUMBERS.ONE));
        playerCards.add(new NumberedSpecialCard(AVAILABLE_CARD_NUMBERS.SEVEN));

        this.cards = playerCards;

    }


}
