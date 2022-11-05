const careerChoice = ['private chef', 'programmer', 'lifeguard', 'postal worker', 'cafeteria worker', 'analyst', 'dog trainer', 'childcare worker'];

const whatIsYourName = name => {
    prompt(`Please enter your name`, 'User');
}

const whatIsYourSkill = skill => {
    prompt(`What is one thing you're really good at`);
    if (this.prompt !== null) {
        prompt(`Can you please enter one thing you're good at`);
    };
}

const careerGenerator = arr => {
    let hypotheticalCareer = careerChoice[Math.floor(Math.random() * careerChoice.length)];
    alert( `${whatIsYourName}, based off of the skill you entered, ${whatIsYourSkill}, our 
    algorithm has calculated that you would be a great ${hypotheticalCareer}.` );
}

const messageGenerator = () => {
    whatIsYourName;
    whatIsYourSkill;
    careerGenerator;
}

messageGenerator;

