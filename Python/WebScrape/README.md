# Automated Job Application System

[![Python 3.12](https://img.shields.io/badge/python-3.12-blue.svg)](https://www.python.org/downloads/)
[![BeautifulSoup](https://img.shields.io/badge/BeautifulSoup-4.12.3-green.svg)](https://www.crummy.com/software/BeautifulSoup/)
[![Playwright](https://img.shields.io/badge/Playwright-1.44.0-orange.svg)](https://playwright.dev/)

## Overview

This project demonstrates an automated job application system that leverages web scraping and browser automation to streamline the job search process. It identifies relevant positions, extracts salary data, and automatically submits applications.

## Features

- ✅ Web scraping of job listings with customizable search criteria
- ✅ Automated job application submission with headless browser support
- ✅ Data analysis and visualization of salary information
- ✅ Modular design with reusable components

## Technical Implementation

### Web Scraping Engine

The scraping module uses BeautifulSoup and requests to extract job data:

- **Pagination handling** for comprehensive data collection across multiple result pages
- **HTML parsing** to extract structured data from unstructured web content
- **Rate limiting** via time delays to respect server limitations
- **Error handling** for robust operation

```python
# Example of the scraper's core functionality:
for title in titles:
    page_num = 1
    while True:
        url = url_link.format(keyword=title.replace(' ','%20'), num=page_num)
        # Process each page and extract job listings
```

### Automation Framework

Built with Playwright, the automation engine:

- **Asynchronous operation** for efficient browser control
- **Selector-based interaction** with form elements
- **Multi-step workflow** automation (navigation, form filling, submission)
- **Cross-browser compatibility** via Chromium

### Data Processing

Using pandas for data manipulation:

- **Structured data storage** in DataFrame format
- **Duplicate elimination** for cleaner datasets
- **String parsing** to extract numeric values from salary ranges
- **Basic visualization** of salary distributions

## Project Structure

```
project/
├── helpers/                 # Module containing reusable components
│   ├── __init__.py          # Package initialization
│   ├── applicant_class.py   # Applicant data model
│   ├── apply_function.py    # Application automation
│   └── job_scraper.py       # Web scraping functionality
├── web_scrape.ipynb         # Jupyter notebook with complete workflow
├── requirements.txt         # Project dependencies
├── CHANGELOG.md             # Version history
└── README.md                # Project documentation
```

## Technologies Used

- **Python** - Core programming language
- **BeautifulSoup** - HTML parsing and web scraping
- **Playwright** - Browser automation
- **Pandas** - Data manipulation and analysis
- **Matplotlib** - Data visualization
- **Jupyter Notebook** - Interactive development and workflow demonstration
- **Asyncio** - Asynchronous programming for browser automation

## Key Concepts Demonstrated

- Object-oriented programming with dataclasses
- Asynchronous programming for web interactions
- Web scraping with proper HTML parsing
- Data transformation and visualization
- Modular code organization
- Type hinting for improved code readability

## Getting Started

1. Clone the repository
2. Install dependencies: `pip install -r requirements.txt`
3. Run the Jupyter notebook for an interactive demo: `jupyter notebook web_scrape.ipynb`
