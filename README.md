# jnTAC
jnTAC is in first place a project I work on in order to learn Java more in-depth. I played around with different typical structures and until now 

## So what is jnTAC?
jnTAC is based on the board game which is known by the name TAC. TAC, in turn, has similarities with the famoues board game called "Mensch ärger dich nicht".
jnTAC is my approach to design the game in Java. In the first stage I want to describe the game mechanics in Java. So until now jnTAC describes how the playing field looks, what mumbles can do, which movements are allowed and which cards can be played.

## Game description
TAC is a game in which each player has the goal to move its four mumbles from the pre field to the home field. The pre field and the home field are connected through the circular structured start field. In order to reach the home field every mumble has to cross the start field. Since the start field is structured circular every mumble has to do one round inside the start field before it can jump to the home field.
While the mumble is inside the start field it can be thrown back to its pre field by a mumble from an opponent. In this case the mumble has to start from the beginning and has to cross the start field.

### How are moves performed?
Every movement in TAC is performed with the usage of a card. Every player gets five cards in the beginning of a round. Clockwise every player is playes one card. After every player used up its cards five more cards are dealt.