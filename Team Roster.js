const team = {
  _players: [
    { firstName: "Ben", lastName: "Franklin", age: 29 },
    { firstName: "John", lastName: "Hancock", age: 34 },
    { firstName: "Kris", lastName: "Doogerson", age: 26 },
  ],
  _games: [
    { opponent: "Billy Badgers", teamPoints: 38, opponentPoints: 17 },
    { opponent: "Philly Poos", teamPoints: 14, opponentPoints: 30 },
    { opponent: "Smashin Bulldogs", teamPoints: 60, opponentPoints: 12 },
  ],
  get players() {
    return this._players;
  },
  get games() {
    return this._games;
  },
  addPlayer(newFirstName, newLastName, newAge) {
    player = { firstName: newFirstName, lastName: newLastName, age: newAge };
    if(newFirstName && newLastName && newAge && newAge >= 0){
    this._players.push(player);
    } else {
      console.log(`Please enter a valid first name, last name, and age for the player.`);
    }
  },
  addGame(newOpponent, newTeamPoints, newOpponentPoints) {
    game = {opponent: newOpponent, teamPoints: newTeamPoints, opponentPoints: newOpponentPoints};
    if(newOpponent && newTeamPoints >=0 && newOpponentPoints >= 0){
      this._games.push(game);
    } else {
      console.log(`Please enter a valid team name and points for both teams that are greater than or equal to zero.`);
    };
  },
};

team.addPlayer("Buggs", "Bunny", 76);
team.addGame('Pickin Pirates', 12, 80);
console.log(team._players);
console.log(team._games);
