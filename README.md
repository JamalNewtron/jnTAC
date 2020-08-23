# jnTAC
jnTAC is in first place a project I work on in order to learn Java more in-depth. I played around with different typical structures and until now I am very happy with the results. Until now 70% to 80% of the game mechanics are implemented. I decided that I will work on the game GUI next. Therefore I want to use OpenGL. So I created another project ongithub which is private. In the long term I want to release the game for the android platform.

## So what is jnTAC?
jnTAC is based on the board game which is known by the name TAC. TAC, in turn, has similarities with the famoues board game called "Mensch ärger dich nicht".
jnTAC is my approach to design the game in Java. In the first stage I want to describe the game mechanics in Java. So until now jnTAC describes how the playing field looks, what mumbles can do, which movements are allowed and which cards can be played.

## Game description
TAC is a game in which each player has the goal to move its four mumbles from the pre field to the home field. The pre field and the home field are connected through the circular structured start field. In order to reach the home field every mumble has to cross the start field. Since the start field is structured circular every mumble has to do one round inside the start field before it can jump to the home field.
While the mumble is inside the start field it can be thrown back to its pre field by a mumble from an opponent. In this case the mumble has to start from the beginning and has to cross the start field.

### How are moves performed?
Every movement in TAC is performed with the usage of a card. Every player gets five cards in the beginning of a round. Clockwise every player is playes one card. After every player used up its cards five more cards are dealt.

## Usage
As said I only implemented the game mechanics. If you really want to test the mechanics feel free to compile the source code into byte code. But watch out there are several debug messages which will confuse you. I hard coded for test reasons two players which will have the same cards. Played cards will not be used up.
Questions in the game:
* Player1: CARD?
	* Input a number between 0 and 4
* Player1: MUMBEL?
	* Input a number between 0 and 3
* Do you really want to placcceee?
	* This question is optional.
	* Input y for yes and n for no.

* Player1: CARD?
	* Input a number between 0 and 4
* Player1: MUMBEL?
	* Input a number between 0 and 3
* Do you really want to placcceee?
	* This question is optional.
	* Input y for yes and n for no.
After both player selected their mumbles and cards the next round can start.
* Do you want to play next round?
	* Input y for yes and n for no.
