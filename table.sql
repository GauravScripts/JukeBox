CREATE DATABASE Jukebox;

use jukebox;
CREATE TABLE `User`(
    `userId` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `userName` VARCHAR(255) NOT NULL UNIQUE,
    `Name` VARCHAR(255) NOT NULL,
    `EmailAddress` VARCHAR(255) NOT NULL UNIQUE,
    `Password` VARCHAR(255) NOT NULL
);

 select songid from musictable where songid =(SELECT min(songid) from musictable);

select songid from musictable where path ='D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\BTS.wav';
select * from user;
select songid from musictable where songid =(SELECT max(songid) from musictable);
ALTER TABLE user AUTO_INCREMENT=100;
CREATE TABLE `musicTable`(
    `songid` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    `artistname` VARCHAR(255) NOT NULL,
    `albumname` VARCHAR(255) NOT NULL,
    `songname` VARCHAR(255) NOT NULL,
    `duration` VARCHAR(255) NOT NULL,
    `genre` VARCHAR(255) NOT NULL,
    `Path` VARCHAR(1023) NOT NULL
);

select * from musictable;
insert into musicTable (artistname,albumname,songname,duration,genre,path) values('BLACKPINK','Born Pink','Pink Venom','03:07','Electronic dance','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\Blackpink.wav');
insert into musicTable (artistname,albumname,songname,duration,genre,path) values('BTS','Love Yourself: Answer','IDOL','03:51','K-pop','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\BTS.wav');
insert into musicTable (artistname,albumname,songname,duration,genre,path) values('Ritviz','Dev','Liggi','03:11','Indian-Pop','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\Liggi.wav');
insert into musicTable (artistname,albumname,songname,duration,genre,path) values('Miley Cyrus','Hannah Montana','Hannah Montana Theme song','00:49','Jazz','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\montana.wav');
insert into musicTable (artistname,albumname,songname,duration,genre,path) values('Queen','News of the World','We Will Rock You','02:14','Rock','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Song\\rock.wav');
CREATE TABLE `podcasttable`(
	id int unsigned not null auto_increment primary key,
    `podcastid` INT UNSIGNED NOT NULL,
    `hostname` VARCHAR(255) NOT NULL,
    `podcastname` VARCHAR(255) NOT NULL,
    `genre` VARCHAR(255) NOT NULL,
    `episodeid` double UNSIGNED NOT Null,
    `episodeName` VARCHAR(255) NOT NULL,
    `episodeUploadDate` varchar(255) NOT NULL,
    `episodeDuration` VARCHAR(255) NOT NULL,
    `Path` VARCHAR(1023) NOT NULL
);
select path from podcasttable where episodeid =1.1;
 select * from podcasttable;
 select episodeid, episodename, episodeduration from podcasttable where podcastid=1;
drop table podcasttable;
truncate table podcasttable;
select distinct(podcastname), podcastid from podcasttable;
select * from podcasttable;
select episodeid, episodename from podcasttable where podcastid=1;
insert into podcasttable (podcastid,hostname,podcastname,genre,episodeid,episodeName,episodeUploadDate,episodeDuration,Path) values(1,'Rahil','3 Things','News Analysis',1.1,'UN counter-terror meet, BCCI bridges pay gap, and case against PhD student','30 Oct. 2022','24:16','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Podcast\\3 Things.wav');
insert into podcasttable (podcastid,hostname,podcastname,genre,episodeid,episodeName,episodeUploadDate,episodeDuration,Path) values(1,'shashank bhargava','3 Things','Short News',1.2,'The Catch Up: 31 October','31 October 2022','03:11','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Podcast\\3 Things_2.wav');
insert into podcasttable (podcastid,hostname,podcastname,genre,episodeid,episodeName,episodeUploadDate,episodeDuration,Path) values(2,'Unkown','UN News - Global perspective Human stories','Expert Analysis',2.1,'Western States living behind wall of denial over Israel’s occupation: UN rights expert','27 October 2022','13:42','D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Podcast\\News.wav');
insert into podcasttable (podcastid,hostname,podcastname,genre,episodeid,episodeName,episodeUploadDate,episodeDuration,Path) values(
3,
'Annie Pforzheimer',
'Opinion Has it',
'Opinions',
3.1,
'America’s Afghan Debacle',
'26 October 2021',
'29:01',
'D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Podcast\\Debt War.wav'
);
insert into podcasttable (podcastid,hostname,podcastname,genre,episodeid,episodeName,episodeUploadDate,episodeDuration,Path) values(
4,
'Rutam Vora',
'The Hindu Businessline - Home',
'News Analysis',
4.1,
'Why Amul is betting big on its new cattle marketplace',
'25 October 2022',
'05:59',
'D:\\A Full Stack Product Engineering\\Wave37 Code\\Java Programs\\JukeBox Project\\JukeBox\\src\\Podcast\\Hindu.wav'
);
select * from musictable;
select * from podcasttable;
CREATE TABLE `userPlaylist`(
    `id1` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `playlistname` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `songid` INT unsigned,
    `id` INT unsigned
);
select distinct(userplaylist.id), podcasttable.episodename, podcasttable.path from userplaylist left join podcasttable  on podcasttable.id = userplaylist.id where playlistname ='podcast';
select distinct(userplaylist.songid), musictable.songname,  musictable.path from userplaylist left join musictable on   musictable.songid = userplaylist.songid where playlistname ='new playlist' and username ='admin';
select distinct(playlistname)from userplaylist where username = 'admin';
truncate table userplaylist;
select * from userplaylist group by playlistname;
select * from userplaylist where playlistname ='new playlist' and username ='admin';
select distinct(playlistname) from userplaylist;
insert into userplaylist() values(1,'new playlist','admin',2,null);
insert into userplaylist values(2,'new playlist1','admin',3,null);
Select * from musicTable;
insert into userplaylist values(9,'new playlist2','root',3,null);
insert into userplaylist(playlistname,username,songid) values('new playlist','admin',4);
insert into userplaylist(playlistname,username,songid) values('new playlist1','admin',2);
select distinct(playlistname) from userplaylist where username='admin';
insert into userplaylist(username,playlistname,songid) values('admin','new playlist',1);
delete from userplaylist where songid =4;
select distinct(songid) from userplaylist where username ='admin' and playlistname ='new playlist1';

select distinct(userplaylist.songid), musictable.songname from userplaylist left join musictable on musictable.songid = userplaylist.songid where playlistname ='new playlist';
ALTER TABLE
    `userPlaylist` ADD FOREIGN KEY(`username`) REFERENCES `User`(`username`);
ALTER TABLE
    `userPlaylist` ADD FOREIGN KEY(`songid`) REFERENCES `musicTable`(`songid`);
ALTER TABLE
    `userPlaylist` ADD  FOREIGN KEY(`id`) REFERENCES `podcasttable`(`id`);

INSERT INTO User (userName,Name,EmailAddress,Password) VALUES ('Lars','Monsen',"example@example.com","toor");
