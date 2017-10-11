--<ScriptOptions statementTerminator=";"/>

CREATE TABLE tb_articles (
	id BIGINT NOT NULL,
	title VARCHAR(255),
	subtitle VARCHAR(255),
	body TEXT,
	date_added TIMESTAMP,
	featured TINYINT,
	archived TINYINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

