CREATE DATABASE blogdb;
USE blogdb;
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50),
                      email VARCHAR(100),
                      password VARCHAR(100),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE
                                  CURRENT_TIMESTAMP
);
CREATE TABLE post (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      user_id INT,
                      title VARCHAR(100),
                      slug VARCHAR(100),
                      image VARCHAR(255),
                      body TEXT,
                      published TINYINT(1),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE
                                  CURRENT_TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
CREATE TABLE topic (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50),
                        slug VARCHAR(50)
);
CREATE TABLE post_like (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           post_id INT,
                           user_id INT,
                           FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
                           FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
CREATE TABLE post_topic (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            post_id INT,
                            topic_id INT,
                            FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
                            FOREIGN KEY (topic_id) REFERENCES topic(id) ON DELETE CASCADE
);