let raceNumber = Math.floor(Math.random() * 1000);
let registeredEarly = true;
let runnerAge = 18;

if (runnerAge > 18 && registeredEarly === true) {
  raceNumber = raceNumber + 1000;
} else {
  raceNumber = raceNumber;
}

if (runnerAge > 18 && registeredEarly === true) {
  console.log(`Hello racer number ${raceNumber}. Your race will begin at 9:30 am.`);
} else if (runnerAge > 18 && registeredEarly === false) {
  console.log(`Hello racer number ${raceNumber}. Your race will begin at 11:00 am.`);
} else if (runnerAge < 18){
  console.log(`Hello racer number ${raceNumber}. Your race will begin at 12:30 pm.`);
} else {
  console.log('Please see the registration desk.');
}
