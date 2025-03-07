# Data Pipeline: Transforming and Loading Customer Data

[![AWS](https://img.shields.io/badge/AWS-Cloud_Services-orange.svg)](https://aws.amazon.com/)
[![Python](https://img.shields.io/badge/Python-ETL_Logic-blue.svg)](https://www.python.org/)
[![SQL](https://img.shields.io/badge/SQL-Data_Querying-green.svg)](https://aws.amazon.com/athena/)
[![AWS Glue](https://img.shields.io/badge/AWS_Glue-ETL_Service-yellow.svg)](https://aws.amazon.com/glue/)

## Overview

This project demonstrates a complete data pipeline implementation for customer data processing, showcasing skills in cloud computing, ETL processes, data engineering, and visualization creation. The pipeline extracts raw customer data from S3, transforms it using AWS Glue and custom Python scripts, loads it into a serverless database, and creates visualizations for business insights.

## Features

- ✅ Cloud-based data storage with AWS S3
- ✅ SQL-based data exploration via AWS Athena
- ✅ Custom transformation logic in Python
- ✅ Automated ETL pipeline with AWS Glue
- ✅ Relational database integration with AWS RDS Aurora
- ✅ Business intelligence dashboards with AWS QuickSight

## Architecture

![Data Pipeline Flow Chart](https://github.com/christianhansonn/PortfolioDataPipeline/blob/main/static/Portfolio%20Project%20Pipeline.jpeg)

## Technical Implementation

### Data Storage and Initial Processing

- **AWS S3** provides scalable object storage for the raw customer data
- **AWS Athena** enables SQL-based data exploration before ETL processing
- **Data validation** checks performed to identify data quality issues

### ETL Process

- **Python transformation logic** handles:
  - Data cleansing and normalization
  - Type conversion and formatting
  - Business rule application
  - Duplicate handling
- **AWS Glue** orchestrates the ETL workflow:
  - Automated metadata discovery with Glue Crawlers
  - Scheduled job execution
  - Resource management and monitoring

```python
# Example of transformation logic pattern:
def clean_customer_data(df):
    # Handle missing values
    # Standardize formats
    # Apply business rules
    return transformed_df
```

### Data Warehousing

- **AWS Aurora Serverless** provides:
  - Relational database storage for transformed data
  - Automatic scaling based on workload
  - Cost efficiency through serverless model
  - High availability and durability

### Data Querying and Visualization

- **SQL-based analysis** enables business insights:
  - Customer segmentation
  - Contact information extraction
  - Payment status analysis
- **AWS QuickSight** delivers:
  - Interactive dashboards
  - KPI tracking
  - Report distribution

## Project Structure

```
Data_Pipeline/
├── Glue/                                   # AWS Glue ETL scripts
│   └── clean.ipynb                         # Python transformation notebook
├── SQL/                                    # SQL queries for analysis
│   └── customer_queries.sql                # Customer segmentation queries
├── static/                                 # Documentation assets
│   └── Portfolio Project Pipeline.jpeg     # Architecture diagram
└── README.md                               # Project documentation
```

## Technologies Used

- **AWS S3** - Cloud object storage
- **AWS Athena** - SQL query service for S3
- **Python** - Custom transformation logic
- **AWS Glue** - ETL service
- **AWS RDS Aurora** - Serverless relational database
- **AWS QuickSight** - Business intelligence service
- **SQL** - Data querying and analysis

## Deployment Process

### AWS Glue ETL and RDS

1. Created custom [Python transformation script](https://github.com/christianhansonn/PortfolioDataPipeline/blob/main/Glue/clean.ipynb) for AWS Glue
2. Configured Glue Crawler to discover metadata from S3 data sources
3. Executed extract and transform processes to clean the dataset
4. Loaded the transformed data into Aurora Serverless MySQL database
5. Validated data integrity after load completion

### Querying and Visualizing the Cleaned Data

The following SQL query identifies non-paying customers with their contact information:

```sql
SELECT
    CONCAT(first_name, ' ', last_name) AS Name,
    phone_number,
    street_address,
    state,
    zip
FROM
    ch_de_portfolio_project_cleaned_customer
WHERE
    paying_customer != 'Y'
ORDER BY 1
```

This query provides critical business intelligence for:

- Call center team prioritization
- Customer outreach campaigns
- Revenue recovery initiatives
- Leadership KPI tracking through QuickSight dashboards

## Key Skills Demonstrated

- Cloud architecture design
- ETL pipeline development
- Data transformation with Python
- SQL query optimization
- Database management
- Business intelligence reporting
- Data engineering best practices
