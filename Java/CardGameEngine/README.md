# Card Game Engine: Hearts

[![Java](https://img.shields.io/badge/Java-OOP-red.svg)](https://www.java.com/)
[![Game Development](https://img.shields.io/badge/Game-Development-purple.svg)](https://en.wikipedia.org/wiki/Game_development)
[![Project Type](https://img.shields.io/badge/Project-Learning-blue.svg)](https://github.com/christianhansonn/PortfolioProject)

## Overview

This project represents my first substantial Java application - a card game engine that implements the classic game of Hearts. Having enjoyed playing Hearts with friends and family for years, I decided to use this familiar game as my introduction to Java programming, object-oriented design principles, and algorithmic thinking.

## Features

- ✅ Complete Hearts game implementation with standard rules
- ✅ Support for 2-6 players with appropriate card removal
- ✅ Automated player turn management and trick taking
- ✅ Proper handling of special cards (Queen of Spades, Heart cards)
- ✅ Score tracking and winner determination
- ✅ Customizable player count
- ✅ Command-line interface with clear game state display

## Technical Implementation

### Core Classes

- **Game** - Manages game flow, rules enforcement, and player coordination:
  - Tracks game state including current trick, player turns, and scoring
  - Implements Hearts-specific rules like preventing hearts lead until broken
  - Handles special case for 2 Clubs (or 2 Spades in 5-player game) as first card
  - Calculates trick winners and point allocation
- **Player** - Handles player-specific logic and card selection:

  - Maintains player's hand of cards
  - Implements card selection strategy based on suit following rules
  - Tracks individual player score through trick accumulation
  - Makes intelligent decisions about which card to play

- **Deck** - Implements card generation, shuffling, and distribution:

  - Creates standard 52-card deck with four suits
  - Supports special case handling for different player counts
  - Manages card distribution to players

- **Main** - Entry point that configures and starts the game:
  - Sets up game with configurable player count
  - Manages game flow and displays results
  - Provides user interface through console output

```java
// Example of the core game loop implementation
public void play() {
    while (maxTricks < 100) {
        // Start a new round
        currentHand.clear();
        deck = new Deck();
        removeCardsForPlayers(numberOfPlayers);

        // Initialize cards per player before shuffle
        int cardsPerPlayer = deck.deck.size() / numberOfPlayers;

        System.out.println("Each player will get " + cardsPerPlayer + " cards.");
        System.out.println("Dealing cards to players...");
        shuffleDeck();
        heartsBroken = false;

        // Find the starting player for the new round
        // ... game logic continues ...
    }
}
```

### Design Patterns & OOP Concepts

- **Object Composition** - Game contains Players, Players contain Hands
- **Encapsulation** - Each class has clear responsibilities and hides implementation details
- **Immutable Objects** - Card representations can't be modified after creation
- **Strategy Pattern** - Card selection logic varies based on game state and rules
- **Factory Method** - Deck creation with appropriate cards for player count

## Learning Journey

This project marks my first serious foray into Java development. As someone familiar with the game of Hearts but new to Java, this project helped me:

1. **Translate Game Rules to Code**: Converting the card game I enjoy with friends and family into a structured program required careful analysis of game mechanics, winning conditions, and special rules.

2. **Understand OOP Fundamentals**: Java's object-oriented nature became clear as I designed classes to represent real-world game entities:

   - Players who make decisions
   - Cards with specific properties
   - Game states and transitions

3. **Learn Java Syntax and Libraries**: The project introduced me to:

   - Collections framework (ArrayList, HashMap, LinkedList)
   - Stream API for functional-style operations
   - Exception handling for robust error management
   - Java code organization and packaging

4. **Develop Algorithm Skills**: Implementing game logic improved my ability to:

   - Design efficient card shuffling algorithms
   - Create trick-taking mechanics
   - Implement scoring systems
   - Manage complex state transitions

5. **Practice Debugging**: Tracking down logical errors in game mechanics proved to be an excellent way to learn debugging techniques.

## Running the Game

To run the game:

1. Clone the repository
2. Navigate to the CardGameEngine directory
3. Compile the Java files: `javac src/*.java`
4. Run the game: `java -cp src Main`

## Game Rules

Hearts is played as follows:

1. Cards are dealt to players (number varies based on player count)
2. Player with 2 of Clubs leads first (2 of Spades in 5-player variant)
3. Players must follow suit if possible
4. Hearts cannot be led until they are "broken" (played on another suit)
5. Players receive points for hearts (1 each) and Queen of Spades (13 points)
6. The goal is to avoid taking points
7. Game ends when any player reaches 100 points
8. Player with lowest score wins

## Technical Reflection

Building this Hearts game engine provided valuable lessons about Java development:

- The importance of proper class design and responsibility separation
- How to model complex rules and game states in code
- Effective use of Java collections and control structures
- Debugging techniques for logic errors in game mechanics
- The value of clear console output for game state visibility

This project represents not just a technical implementation, but also a personal milestone in my software development journey - transforming a card game I've enjoyed with friends and family into a fully functional Java application.
