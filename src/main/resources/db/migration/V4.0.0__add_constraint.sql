--alter table station_on_postcode
--    owner to postgres;


--ALTER TABLE Persons
--    ADD CONSTRAINT PK_Person PRIMARY KEY (ID,LastName);
--ALTER TABLE users ADD CONSTRAINT fk_grade_id FOREIGN KEY (grade_id) REFERENCES grades(id);

ALTER TABLE data.postcode_of_station ADD CONSTRAINT foreign_key_country FOREIGN KEY (country) REFERENCES data.country(id_country);
ALTER TABLE data.measurement_on_postcode_of_station ADD CONSTRAINT postcode FOREIGN KEY (postcode) REFERENCES data.postcode_of_station(id_postcode_of_station);
ALTER TABLE data.measurement_on_postcode_of_station ADD CONSTRAINT param FOREIGN KEY (param) REFERENCES data.param(id_param);
ALTER TABLE data.postcode_of_patient ADD CONSTRAINT country_foreign_key FOREIGN KEY (country) REFERENCES data.country(id_country);
ALTER TABLE data.postcode_of_patient ADD CONSTRAINT postcode_foreign_key FOREIGN KEY (matching_post_code) REFERENCES data.postcode_of_station(id_postcode_of_station);
ALTER TABLE data.station_on_postcode ADD CONSTRAINT foreign_key_postcode_station FOREIGN KEY (postcode_id) REFERENCES data.postcode_of_station(id_postcode_of_station);

