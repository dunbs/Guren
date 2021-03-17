CREATE TABLE user (
	id integer PRIMARY KEY AUTOINCREMENT,
	username varchar,
	password varchar,
	email blob,
	nick_name integer,
	created_date datetime,
	is_deleted integer
);

CREATE TABLE group (
	id integer PRIMARY KEY AUTOINCREMENT,
	creator_id integer,
	created_date datetime
);

CREATE TABLE group_member (
	user_id integer,
	group_id integer,
	role_id integer,
	joined_date datetime
);

CREATE TABLE job (
	id blob PRIMARY KEY AUTOINCREMENT,
	group_id integer,
	creator_id integer,
	deadline datetime,
	created_date datetime,
	is_deleted integer
);

CREATE TABLE job_assignee (
	id integer PRIMARY KEY AUTOINCREMENT,
	job_id integer,
	user_id integer,
	assigner_id integer,
	created_date datetime,
	is_deleted integer PRIMARY KEY AUTOINCREMENT
);

CREATE TABLE role (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar
);

CREATE TABLE permission (
	id integer PRIMARY KEY AUTOINCREMENT,
	name integer PRIMARY KEY AUTOINCREMENT
);

CREATE TABLE role_permission (
	role_id integer,
	permission_id integer
);

