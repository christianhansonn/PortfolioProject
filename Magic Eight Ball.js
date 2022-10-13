let userName = "";
if (userName) {
  console.log(`Hello, ${userName}!`)
} else {
  let defaultName = 'Player One'
  userName = defaultName
  console.log(`Hello, ${userName}!`);
} //sets player name to player one if none is entered

let userQuestion = 'Am I lucky?'
console.log(`${userName} asks, '${userQuestion}'.`);

let randomNumber = Math.floor(Math.random() * 8); //creates whole number 
let eighBall = ''

switch(randomNumber) {
  case 0 :
    eightBall = 'It is certain';
    break;
  case 1 :
    eightBall = 'It is decidely so';
    break;
  case 2 :
    eightBall = 'Reply hazy try again';
    break;
  case 3 :
    eightBall = 'Cannot predict now';
    break;
  case 4 :
    eightBall = 'Do not count on it';
    break;
  case 5 :
    eightBall = 'My sources say no';
    break;
  case 6 :
    eightBall = 'Outlook not so good';
    break;
  case 7 :
    eightBall = 'Signs point to yes';
    break;
} //sets result based on number generated in randomNumber

console.log(eightBall); //prints result
