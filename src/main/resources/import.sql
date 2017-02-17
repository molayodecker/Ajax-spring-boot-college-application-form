-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id) values ('user',true,'password',1);
insert into user (username,enabled,password,role_id) values ('user2',true,'password',1);



insert into exams (courseOffered,examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('science','WASSCE','English','A2','123343444','2010',true,1);
insert into exams (courseOffered,examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('science','WASSCE','English','A2','123343444','2010',true,1);

insert into certificateprogramme (nationality,date,email,fullname,gender,guardianfullname,guardiantelephonenumber,hometown,language,married, postaladdress,region,telephonenumber,user_id) values('Ghanaian','06-06-06', 'molayodecker','molayo decker', 'Male', 'Yvonne Decker','3019791778','Aburi','Twi','Single','P.O.BOX MS 678','Greater Accra', '0549791778',1);
