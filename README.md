# GameFlix

GameFlix is a school project built by Cooper Campbell for HCDD 412 at Penn State.

## AI

This section documents how AI tools were used in this project.

**Tool(s) used:** Claude

| Date    | Area                   | Output                                                                                 | Changes                                                                                                                                      |
|---------|------------------------|----------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| 10/19   | **File:** README.md    | Claude generated the README.md file with little to no problems.                        | I verified everything, cleaned up styling, and simplified usage instructions. I also added the AI, changes, database, and security sections. |
| 10/19   | **Class:** JWT Service | Claude generated most of the JWT Service class, but it had sloppy and redundent logic. | I cleaned up the *generateToken* method, so it worked for both registration/login.                                                           |

## Changes

This section documents changes made to the project.

| Date | Changes                                                          |
|------|------------------------------------------------------------------|
| 10/19 | Created User Model and Service                                   |
| 10/19 | Created Auth Controller with Login/Registration Endpoints        |
| 10/19 | Created User Controller with Authenticated Current User Endpoint |
| 10/22 | Changed User ID from Long to UUID                                |
| 10/22 | Removed Email from Authentication Token                          |
| 10/22 | Created Game Model and Service                                   |
| 10/22 | Created Catalog Service |
| 10/22 | Created Catalog Controller with Authenticated Catalog Route |
| 10/22 | Created Review Model and Service |
| 11/1 | Created Review Controller |


## Database

This section documents database choices and how each table is created.

**spring.jpa.hibernate.ddl-auto**: Update  
I chose update so Hibernate creates and updates the tables automatically from my model classes, which lets me update models without having to manually migrate the schema. Since this is a class project, it's easier to keep deliverables up-to-date since data is never dropped.

| Table | Statement |
|-------|-----------|
| Users | CREATE TABLE `users` (`id` bigint NOT NULL AUTO_INCREMENT,`created_at` datetime(6) NOT NULL,`email` varchar(255) NOT NULL,`name` varchar(255) NOT NULL,`password_hash` varchar(255) NOT NULL,`updated_at` datetime(6) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci |

## Security

This section documents security choices.

**Hashing Algorithm**: Bcrypt  
While Argon2 is considered superior to Bcrypt, I chose Bcrypt because it's been around for a longer period of time and has proven itself to be reliable. It also uses less memory and requires fewer dependencies, which fits the scope of this project better.

**Work Factor**: 12  
Since OWASP recommends a minimum work factor of 10, I used a work factor of 12 to provide an extra margin of security against brute-force attacks. The timing difference won't be noticed at this scale and the extra security is worth it in a modern web application.

**JWT Expiration**: 1 Hour  
I set tokens to expire after one hour to balance security and convenience. A short expiration of one hour limits how long a stolen token would be useful, while also ensuring a user isn’t logged out in the middle of a session.

## Running the Application

This section explains how to run GameFlix locally.

### Prerequisites

- Java 17 or newer
- Maven (or use the included `./mvnw` wrapper)
- MySQL 8.0 or newer
- OpenSSL (to generate the JWT signing key)

### 1. Create your configuration file

Configuration lives in `src/main/resources/`. The repository includes `application.properties.example`, which contains **every value that must be changed before the app will run**. Any value wrapped in curly braces, such as `{LIKE_THIS}`, is a placeholder that you must replace (including the braces).

Copy the example file:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Do not commit `application.properties`. It contains your database password and signing key. Only the `.example` file belongs in version control.

### 2. Set up MySQL

**Create the database and user**

The application expects its own database and a dedicated user that can only access that database. Run these statements as a MySQL admin (for example, `mysql -u root -p`):

```sql
CREATE DATABASE gameflix CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE USER 'gameflix_user'@'localhost' IDENTIFIED BY 'choose-a-strong-password';

GRANT ALL PRIVILEGES ON gameflix.* TO 'gameflix_user'@'localhost';

FLUSH PRIVILEGES;
```

You do not need to create any tables. Hibernate creates and updates them on startup (see the Database section below). The user needs full privileges on the `gameflix` database for this reason, and nothing outside it.

**Configure the data source**

In `application.properties`, find the datasource block:

```properties
spring.datasource.url=jdbc:mysql://{DB_URL}:{DB_PORT}/{DB_NAME}
spring.datasource.username={DB_USERNAME}
spring.datasource.password={DB_PASSWORD}
```

Replace each placeholder with the values from the SQL above. The finished block looks like this:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gameflix
spring.datasource.username=gameflix_user
spring.datasource.password=choose-a-strong-password
```

### 3. Generate a JWT signing key

Tokens are signed with a secret key made of 32 random bytes, written as 64 hex characters:

```bash
openssl rand -hex 32
```

Paste the output into the JWT secret placeholder in `application.properties`.

### 4. Start the application

```bash
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`. On the first run, check the MySQL `gameflix` database: the `users` table should now exist.