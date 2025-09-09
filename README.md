# Match Analytics Project

## Overview
This web application provides analytics for football matches, tracking **players**, **events**, and **matches**.  
- **Backend:** Spring Boot (Java)  
- **Frontend:** Angular  
- **Database:** PostgreSQL  

The backend automatically seeds example data from `match.json` on first run.

---

## Backend Setup (Spring Boot)

### Prerequisites
- Java 17+  
- Maven 3.8+  
- PostgreSQL 15+  

### Database Setup
1. Create the database `matchAnalytics`:


2. Update your application.properties:
         spring.datasource.url=jdbc:postgresql://localhost:5432/matchAnalytics
         spring.datasource.username=your_username
         spring.datasource.password=your_password
         spring.jpa.hibernate.ddl-auto=update
         spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

3. Run the Backend
   mvn clean spring-boot:run
----
The backend runs at: http://localhost:8080
The database will be seeded automatically using src/main/resources/seed/match.json.

| Method | URL                            | Description           | Request Body      | Response                                     |
| ------ | ------------------------------ | --------------------- | ----------------- | -------------------------------------------- |
| `POST` | `/api/event`                   | Create a new event    | `EventDTO` (JSON) | Saved `Event` object                         |
| `GET`  | `/api/event`                   | Get all events        | None              | List of events with assist populated         |
| `GET`  | `/api/player/{playerId}/stats` | Get player statistics | None              | `PlayerStatsDTO` with goals, assists, rating |


## FrontEnd Setup (Angular)
### Prerequisites
- Node.js 18+
- Angular CLI 16+
 
1. Install Dependencies
      npm install

2. Run Angular Application
      ng serve
----
The frontend runs at: http://localhost:4200
Make sure the backend server is running on port 8080.

## Docker Setup
### Prerequisites
Docker 24+
Docker Compose 2+

1. Build and Run Containers
   docker-compose up --build
----
2. Accessing Services
Backend API: http://localhost:8080
Database: localhost:5432




----
### Notes 
All data is stored in PostgreSQL.

Backend automatically maps meta and assist fields for events.

Frontend communicates with backend APIs to display matches, players, events.

match-analytics/
├── backend/           # Spring Boot backend
│   ├── src/main/java/com/matchAnalytics
│   ├── src/main/resources
│   │   └── seed/match.json
├── match-analyze-frontend/          # Angular frontend
│   ├── src/
│   └── package.json
│── README.md
└── docker-compose.yml



