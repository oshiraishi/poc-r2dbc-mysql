create table case_tbl(id VARCHAR(50) NOT NULL,
description VARCHAR(100) NOT NULL,
create_date_local_time DATETIME,
created_date_offset_time TIMESTAMP,
last_update_offset_ime TIMESTAMP,
detail JSON,
PRIMARY KEY ( id ));
