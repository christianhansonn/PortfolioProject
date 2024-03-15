import { test, expect } from '@playwright/test';
import { chromium } from 'playwright';
import * as constants from '../../loginCredentials.js';
import { generateVin } from '../../logic/vinNumberGenerator.js';
const faker = require('faker');
const fs = require('fs');

let globalCookies = [];
let browser;
let page;
let generatedFields = [];

test.beforeAll(async () => {
  browser = await chromium.launch();
  const context = await browser.newContext();
  page = await context.newPage();
  test.setTimeout(180000);
  await page.goto(constants.sysTestLink);
  await page.getByLabel('I agree to the Terms of').check();
  await page.getByRole('button', { name: 'Single Sign-on Login' }).click();
  await page.waitForTimeout(2000);
  await page.getByPlaceholder('Email, phone, or Skype').click();
  await page.getByPlaceholder('Email, phone, or Skype').fill(constants.loginEmail);
  await page.getByRole('button', { name: 'Next' }).click();
  await page.locator('#i0118').click();
  await page.getByPlaceholder('Password').click({
    modifiers: ['Control']
  });
  await page.getByPlaceholder('Password').fill(constants.loginPassword);
  await page.getByRole('button', { name: 'Sign in' }).click();
  await page.getByRole('button', { name: 'Text +X XXXXXXXX39‎' }).click();
  await page.pause();
  const yesButton = page.getByRole('button', { name: 'Yes' });
  if (await yesButton.isVisible()) {
    await yesButton.click();
  }
  globalCookies = await context.cookies();

  await page.goto(constants.sysTestLink, {
    waitUntil: 'networkidle',
    timeout: 120000
  });

  await page.getByRole('button', { name: 'Module logo Property' }).click();
  await page.getByRole('link', { name: 'Form Entry' }).click();
  await page.click('#approvalRequired');
  await page.getByRole('button', { name: 'Return of Tangible Personal Property Motor Vehicle' }).click();
  await page.getByRole('button', { name: 'Next' }).click();
  await page.getByRole('button', { name: 'Continue' }).click();
});

test.beforeEach(async ({ context }) => {
  await context.addCookies(globalCookies);
});

test.describe.serial('Revx Regression Suits', () => {
  for (let year = 2023; year >= 1988; year--) {
    test(`Assessed Value Not Provided by NADA - ${year}`, async () => {

      const userStoryId = 'CHES-367';

      console.log(`Testing User Story: ${userStoryId} - Year: ${year}`);

      const ownerName = faker.name.findName();
      const ownerSSN = faker.random.number({ min: 100000000, max: 999999999 });
      const ownerAddress = faker.address.streetAddress();
      const purchasePrice = faker.random.number({ min: 1000, max: 999999 });
      const vinNumber = generateVin();
      const titleNumber = faker.random.number({ min: 10000000, max: 99999999 });
      let taxYear = faker.random.number({min: 2020 , max: 2023});
      taxYear = year > taxYear ? year : taxYear;
      
      const generatedData = {
        ownerName,
        ownerSSN,
        ownerAddress,
        purchasePrice,
        vinNumber,
        titleNumber,
        'Purchase Year': year.toString(),
        'Tax Year' : taxYear.toString()
      };

      await page.getByLabel("Owner's Name").click();
      await page.getByLabel("Owner's Name").fill(ownerName.toString());
      await page.getByLabel('SSN / FEIN (9 characters)').click();
      await page.getByLabel('SSN / FEIN (9 characters)').fill(ownerSSN.toString());
      await page.getByLabel('ID Type').click();
      await page.getByRole('option', { name: 'SSN' }).click();
      await page.getByLabel('Address 1').click();
      await page.getByLabel('Address 1').fill(ownerAddress.toString());
      await page.getByLabel('City').first().click();
      await page.getByLabel('City').first().fill('Chesapeake');
      await page.getByLabel('State').first().click();
      await page.getByRole('option', { name: 'Virginia' }).first().click();
      await page.getByLabel('Zip (5 characters)').click();
      await page.getByLabel('Zip (5 characters)').fill('23320');
      await page.getByLabel('Sale Price').click();
      await page.getByLabel('Sale Price').fill(purchasePrice.toString());
      await page.getByLabel('Tax Year').click();
      await page.getByRole('option', { name: taxYear }).click();
      await page.getByLabel('Year Purchased').click();
      await page.getByLabel('Year Purchased').fill(year.toString());
      await page.getByLabel('VIN (17 characters, no I O or Q)').click();
      await page.getByLabel('VIN (17 characters, no I O or Q)').fill(vinNumber.toString());
      await page.getByLabel('Vehicle Make').click();
      await page.getByRole('option', { name: 'TESLA' }).click();
      await page.getByLabel('Title Number (8 or 10 digits)').click();
      await page.getByLabel('Title Number (8 or 10 digits)').fill(titleNumber.toString());
      await page.getByLabel('Vehicle Type').click();
      await page.getByRole('option', { name: 'Automobile (Car/Van)' }).click();
      await page.getByLabel('Tax Exemption').click();
      await page.getByRole('option', { name: 'No Exemption' }).click();
      await page.getByLabel('sections.0.layouts.4.fields.1').click();
      await page.getByLabel('sections.0.layouts.4.fields.1').fill(`01/01/${year}`);
      await page.getByLabel('checkbox label').check();
      await page.getByRole('button', { name: 'Save' }).click();
      generatedFields.push(generatedData);
    });
  }

  test.afterAll(async () => {
    // Generate a timestamp for the filename
    const timestamp = new Date().toISOString().replace(/:/g, '-').replace(/\./g, '-');
    const filename = `generatedFields_${timestamp}.json`;

    // Write the generated fields to a JSON file with a timestamp
    fs.writeFileSync(filename, JSON.stringify(generatedFields, null, 2));
  });
});
