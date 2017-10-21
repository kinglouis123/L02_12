CREATE TABLE classes(
	coursecode CHAR(8) NOT NULL,
	semester TEXT NOT NULL,
	year CHAR(4) NOT NULL,
	accesscode INTEGER,
	instructorID INTEGER NOT NULL,
	PRIMARY KEY (coursecode,semester, year, instructorID)
);


INSERT INTO classes VALUES ('STAB22H3','FALL','2017',12345678,100);