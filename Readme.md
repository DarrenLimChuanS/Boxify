![Boxify](https://user-images.githubusercontent.com/3714496/101014246-88bc1880-35a0-11eb-8e7e-27537b0a3c17.png)

## Table of Content

1. [ Introduction ](#introduction)
2. [ How to Set Up? ](#setup)
3. [ Running of Tests ](#test)
4. [ Architecture ](#architecture)
5. [ Features ](#features)
6. [ Technologies Used ](#technologies)
7. [ Team ](#team)

<a name="setup"></a>
Documentation: https://trusting-nobel-88a626.netlify.app/
## Steps to Setup the Spring Boot Back end app (server)

1. **Clone the application**

   ```bash
   git clone https://github.com/DarrenLimChuanS/Boxify.git
   cd server
   ```

2. **Create MySQL database**

   ```bash
   create database <project_name>
   ```

3. **Change MySQL username and password as per your MySQL installation**

   - open `src/main/resources/application.properties` file.

   - change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

   You can run the spring boot app by typing the following command -

   ```bash
   mvn spring-boot:run
   ```

   The server will start on port 8080.

   You can also package the application in the form of a `jar` file and then run it like so -

   ```bash
   mvn package
   java -jar target/polls-0.0.1-SNAPSHOT.jar
   ```

5. **Default Roles**

   The spring boot app uses role based authorization powered by spring security. To add the default roles in the database, I have added the following sql queries in `src/main/resources/data.sql` file. Spring boot will automatically execute this script on startup -

   ```sql
   INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
   INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');
   ```

   Any new user who signs up to the app is assigned the `ROLE_USER` by default.

## Steps to Setup the React Front end app (client)

First go to the `client` folder -

```bash
cd client
```

Then type the following command to install the dependencies and start the application -

```bash
npm install && npm start
```

The front-end server will start on port `3000`.

<a name="testing"></a>

## Testing

<b>Client: </b>Component Testing

```bash
npm run test
```

<a name="features"></a>

## Features

<ol>
  <li>Login</li>
  <li>Register</li>
  <li>User Profile</li>
</ol>

<a name="technologies"></a>

## Technologies Used

### Front-End

<ul>
  <li><a href="https://reactjs.org/">ReactJS</a></li>
  <li><a href="https://pro.ant.design/">Ant Design Pro</a></li>
  <li><a href="https://jestjs.io/">Jest (Testing Framework)</a></li>
</ul>

### Back-End

<ul>
  <li><a href="https://spring.io/">Spring Boot</a></li>
  <li><a href="https://www.mysql.com/">MySQL</a></li>
</ul>

### Continuous Integration

<ul>
  <li><a href="https://travis-ci.com/">Travis CI</a></li>
  <li><a href="https://www.heroku.com/">Heroku</a></li>
</ul>

<a name="team"></a>

## Team

<b>Proudly Developed by:</b>

<ol>
<li><a href="https://github.com/thefiend">Al-Akmalul Fitri Bin Abu Bakar</a></li>
  <li><a href="https://github.com/DarrenLimChuanS">Aster Teo</a></li>
  <li><a href="https://github.com/thefiend">Huo Weifeng</a></li>
  <li><a href="https://github.com/thefiend">Kam Ming Feng</a></li>
  <li><a href="https://github.com/DarrenLimChuanS">Darren Lim</a></li>
</ol>
