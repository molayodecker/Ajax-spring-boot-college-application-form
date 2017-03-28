-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id) values ('user',true,'$2a$10$pZCayssAtjCChDbXAg6naeAfYTj3KIwe6sizXWw6/LxUn7tvlGMd6',1);
insert into user (username,enabled,password,role_id) values ('user2',true,'$2a$10$pZCayssAtjCChDbXAg6naeAfYTj3KIwe6sizXWw6/LxUn7tvlGMd6',1);



insert into exams (examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('WASSCE','English','A2','123343444','2010',true,1);
insert into exams (examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('WASSCE','English','A2','123343444','2011',true,1);
insert into exams (examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('WASSCE','English','A2','123343444','2012',true,1);
insert into exams (examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('WASSCE','English','A2','123343444','2013',true,1);
insert into exams (examType,subject,grade,indexNumber,gradeYear,isComplete,user_id) values ('WASSCE','English','A2','123343444','2014',true,1);

insert into certificateprogramme (courseOffered,nationality,date,email,fullname,gender,guardianfullname,guardiantelephonenumber,hometown,language,married, postaladdress,region,telephonenumber,user_id, flag) values('science','Ghanaian','2016-06-06', 'molayodecker','molayo decker', 'Male', 'Yvonne Decker','3019791778','Aburi','Twi','Single','P.O.BOX MS 678','Greater Accra', '0549791778',1,false);

insert into picture (bytes,uploadfile,user_id) values ('ffd8ff','WWDC-New-Macbook-Pro.jpg',1);
--insert into Logger (redirect) VALUES ('/');