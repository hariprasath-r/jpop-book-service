CREATE TABLE BOOKS (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  book_name varchar(100) NOT NULL,
  description varchar(512) NOT NULL,
  genre varchar(20) NOT NULL,
  author varchar(100) NOT NULL,
  PRIMARY KEY (id)
);