## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE hms_dev;
CREATE DATABASE hms_prod;

#Create database service accounts
CREATE USER 'hms_dev_user'@'localhost' IDENTIFIED BY 'hotel';
CREATE USER 'hms_prod_user'@'localhost' IDENTIFIED BY 'hotel';
CREATE USER 'hms_dev_user'@'%' IDENTIFIED BY 'hotel';
CREATE USER 'hms_prod_user'@'%' IDENTIFIED BY 'hotel';

#Database grants
GRANT SELECT ON hms_dev.* to 'hms_dev_user'@'localhost';
GRANT INSERT ON hms_dev.* to 'hms_dev_user'@'localhost';
GRANT DELETE ON hms_dev.* to 'hms_dev_user'@'localhost';
GRANT UPDATE ON hms_dev.* to 'hms_dev_user'@'localhost';
GRANT SELECT ON hms_prod.* to 'hms_prod_user'@'localhost';
GRANT INSERT ON hms_prod.* to 'hms_prod_user'@'localhost';
GRANT DELETE ON hms_prod.* to 'hms_prod_user'@'localhost';
GRANT UPDATE ON hms_prod.* to 'hms_prod_user'@'localhost';
GRANT SELECT ON hms_dev.* to 'hms_dev_user'@'%';
GRANT INSERT ON hms_dev.* to 'hms_dev_user'@'%';
GRANT DELETE ON hms_dev.* to 'hms_dev_user'@'%';
GRANT UPDATE ON hms_dev.* to 'hms_dev_user'@'%';
GRANT SELECT ON hms_prod.* to 'hms_prod_user'@'%';
GRANT INSERT ON hms_prod.* to 'hms_prod_user'@'%';
GRANT DELETE ON hms_prod.* to 'hms_prod_user'@'%';
GRANT UPDATE ON hms_prod.* to 'hms_prod_user'@'%';