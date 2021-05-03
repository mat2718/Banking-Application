create table member_account_type (
	id serial not null primary key,
	type varchar not null);	

-- Creating member account
CREATE TABLE member_account (
	email varchar UNIQUE not null primary key,
	creation_date date not null default CURRENT_DATE,-- added this COLUMN FOR jee wiz info
	password varchar NOT NULL,
	status char(1) DEFAULT '1',
	member_type int not null default '1', -- 1 is standard customer 2 is admin user
	constraint member_type_fk1 foreign key (member_type) references member_account_type(id));-- status IS 1 FOR OPEN AND 2 FOR closed

-- Customer information	
-- Setup as a different table only for future flexibility	
CREATE TABLE customer_information (
	id serial not null primary key,
	first_name varchar not null,
	last_name varchar not NULL,
	member_id varchar NOT NULL,
	constraint customer_member_fk1 foreign key (member_id) references member_account(email));
	
-- Bank accounts and total remaining balance
-- Foreign key is the member id
CREATE TABLE bank_account (
	id serial not null primary key,
	account_type varchar not null,
	balance float not null default 0,
	status char(1) default '1', -- allows a member to close an account without deleting it 
	member_id varchar NOT NULL,
	constraint bank_member_fk1 foreign key (member_id) references member_account(email));

-- All transaction history
-- Foreign key is the bank account tied to the transaction
CREATE TABLE transaction_history (
	id serial not null primary key,
	description varchar not null,
	action_date date not null default CURRENT_DATE,
	amount float not null,
	bank_account_id int NOT NULL,
	constraint transaction_bank_fk1 foreign key (bank_account_id) references bank_account(id));

-- create member type 
INSERT INTO member_account_type (type) VALUES('customer');
INSERT INTO member_account_type (type) VALUES('manager');
INSERT INTO member_account_type (type) VALUES('admin');

-- create some member accounts
INSERT INTO member_account (email, password) VALUES('esokullu@hotmail.com', 'kitty');
INSERT INTO member_account (email, password) VALUES('wsnyder@verizon.net', 'school');
INSERT INTO member_account (email, password) VALUES('jbuchana@sbcglobal.net', 'old');
INSERT INTO member_account (email, password) VALUES('mcnihil@me.com', 'waves');
INSERT INTO member_account (email, password) VALUES('hermes@gmail.com', 'defective');
INSERT INTO member_account (email, password) VALUES('jespley@me.com', 'plane');
INSERT INTO member_account (email, password) VALUES('marnanel@verizon.net', 'table');
INSERT INTO member_account (email, password) VALUES('boser@aol.com', 'uninterested');
INSERT INTO member_account (email, password) VALUES('keutzer@aol.com', 'machine');
INSERT INTO member_account (email, password) VALUES('mlewan@aol.com', 'nondescript');
INSERT INTO member_account (email, password) VALUES('balchen@aol.com', 'macho');
INSERT INTO member_account (email, password) VALUES('cgcra@mac.com', 'verdant');
INSERT INTO member_account (email, password) VALUES('wiseb@comcast.net', 'x-ray');
INSERT INTO member_account (email, password) VALUES('gastown@optonline.net', 'interesting');
INSERT INTO member_account (email, password) VALUES('engelen@verizon.net', 'nimble');
INSERT INTO member_account (email, password) VALUES('seebs@verizon.net', 'lake');
INSERT INTO member_account (email, password) VALUES('houle@comcast.net', 'powerful');
INSERT INTO member_account (email, password) VALUES('choset@verizon.net', 'known');
INSERT INTO member_account (email, password) VALUES('sfoskett@icloud.com', 'scared');
INSERT INTO member_account (email, password) VALUES('maradine@mac.com', 'face');
INSERT INTO member_account (email, password) VALUES('british@verizon.net', 'addicted');
INSERT INTO member_account (email, password) VALUES('mirod@comcast.net', 'shade');
INSERT INTO member_account (email, password) VALUES('drhyde@optonline.net', 'push');
INSERT INTO member_account (email, password) VALUES('frostman@aol.com', 'godly');
INSERT INTO member_account (email, password) VALUES('kidehen@me.com', 'unsightly');
INSERT INTO member_account (email, password) VALUES('pjacklam@msn.com', 'month');
INSERT INTO member_account (email, password) VALUES('shang@me.com', 'nauseating');
INSERT INTO member_account (email, password) VALUES('wiseb@yahoo.ca', 'glove');
INSERT INTO member_account (email, password) VALUES('jeffcovey@icloud.com', 'simple');
INSERT INTO member_account (email, password) VALUES('treeves@msn.com', 'refuse');
INSERT INTO member_account (email, password, member_type) VALUES('admin@gmail.com', '1234',3);
INSERT INTO member_account (email, password, member_type) VALUES('manager@gmail.com', '1234',2);
INSERT INTO member_account (email, password) VALUES('test@gmail.com', '1234');

-- create some customer info
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Smith', 'James','esokullu@hotmail.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Rios', 'Gloria','wsnyder@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Moore', 'Lexi','jbuchana@sbcglobal.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Matthews', 'Presley','mcnihil@me.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Newman', 'Maximus','hermes@gmail.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Gomez', 'Kylie','jespley@me.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Christensen', 'Danna','marnanel@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Silva', 'Izabella','boser@aol.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Duncan', 'Alondra','keutzer@aol.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Cook', 'Zechariah','mlewan@aol.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Jordan', 'Harold','balchen@aol.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Zhang', 'Gisselle','cgcra@mac.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Heath', 'Melissa','wiseb@comcast.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Sexton', 'Whitney','gastown@optonline.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Lindsey', 'Dario','engelen@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Wiley', 'Sebastian','seebs@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Mooney', 'Ashleigh','houle@comcast.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Wood', 'Aspen','choset@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Ray', 'Michael','sfoskett@icloud.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Leach', 'Jonah','maradine@mac.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Ball', 'Rubi','british@verizon.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Yoder', 'Corinne','mirod@comcast.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Cain', 'Ali','drhyde@optonline.net');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Mullen', 'Ricky','frostman@aol.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Gay', 'Jagger','kidehen@me.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Lozano', 'Marlene','pjacklam@msn.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Barber', 'Carlos','shang@me.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Page', 'Ernest','wiseb@yahoo.ca');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Obrien', 'Yesenia','jeffcovey@icloud.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('Meyer', 'Xander','treeves@msn.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('terry', 'matt','admin@gmail.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('terry', 'matt','manager@gmail.com');
INSERT INTO customer_information (first_name, last_name, member_id) VALUES('terry', 'matt','test@gmail.com');


INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'esokullu@hotmail.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'wsnyder@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'jbuchana@sbcglobal.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'mcnihil@me.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'hermes@gmail.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'jespley@me.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'marnanel@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'boser@aol.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'keutzer@aol.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'mlewan@aol.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'balchen@aol.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'cgcra@mac.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'wiseb@comcast.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'gastown@optonline.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'engelen@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'seebs@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'houle@comcast.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'choset@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'sfoskett@icloud.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'maradine@mac.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'british@verizon.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'mirod@comcast.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'drhyde@optonline.net');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'frostman@aol.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'kidehen@me.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'pjacklam@msn.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'shang@me.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'wiseb@yahoo.ca');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'jeffcovey@icloud.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1000,'treeves@msn.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Checking',1000,'test@gmail.com');
INSERT INTO bank_account (account_type, balance, member_id) VALUES('Savings',1500,'test@gmail.com');




