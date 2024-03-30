<div align="center">
  <h1 align="center"><a href="https://github.com/ASJordi/url-shortener-java">URL Shortener</a></h1>

  <p align="center">URL Shortener with Java and Jakarta EE</p>
</div>

## About :computer:

URL Shortener is a web application that allows you to shorten URLs with a custom alias. To do this, it uses MySQL to store the URLs and the aliases. Implements a AUTH_CODE to protect the creation of new URLs. The application is developed with Java and Jakarta EE.

<img src="src/main/resources/app01.png" alt="app01" width="400"/>
<img src="src/main/resources/app02.png" alt="app01" width="400"/>

## Stack :hammer_and_wrench:

* Java SE
* Jakarta EE
* JSP
* JSTL
* CDI
* Tomcat
* MySQL
* CSS
* Water.css

## Installation :gear:

- Clone the repository

  ```bash
  git@github.com:ASJordi/url-shortener-java.git
  ```

- Create a MySQL database with the script in `src/main/resources/db/db.sql`

- Configure the database connection in `src/main/webapp/META-INF/context.xml`

- Install dependencies with Maven

- Configure tomcat to use the MySQL connector

- Run the application with:

  ```bash
  mvn tomcat7:redeploy
  ```

## License :page_facing_up:

Distributed under the MIT License. See `LICENSE` for more information.

## Contact :email:

Jordi Ayala - [@ASJordi](https://twitter.com/ASJordi)

Project Link: [https://github.com/ASJordi/url-shortener-java](https://github.com/ASJordi/url-shortener-java)
