
drop table if exists shows;
drop table if exists episodes;
drop table if exists seasons;
drop table if exists users;
drop table if exists history;
drop table if exists favorite;
drop table if exists comments;

create table shows (
    id integer primary key autoincrement,
    title text not null,
    description text not null,
    imageUrl text not null,
    releaseDate text not null,
    genre text not null,
    rating integer not null
);


create table seasons (
    id integer primary key autoincrement,
    showId integer not null,
    title text not null,
    description text not null,
    imageUrl text not null,
    releaseDate text not null,
    foreign key (showId) references shows(id)
);


create table episodes (
    id integer primary key AUTOINCREMENT,
    seasonId integer not null,
    title text not null,
    episodeNumber integer not null,
    length integer,
    imageUrl text not null,
    videoUrl text not null,
    foreign key (seasonId) references seasons(id)
);

create table users (
    id integer primary key autoincrement,
    username text not null,
    password text not null,
    email text not null,
    isAdmin integer not null
);

create table history (
    id integer primary key autoincrement,
    userId integer not null,
    episodeId integer not null,
    date date not null,
    foreign key (userId) references users(id),
    foreign key (episodeId) references episodes(id)
);

create table favorite (
    userId integer not null,
    showId integer not null,
    foreign key (userId) references users(id),
    foreign key (showId) references shows(id)
);

create table comments (
    id integer primary key autoincrement,
    userId integer not null,
    episodeId integer not null,
    comment text not null,
    foreign key (userId) references users(id),
    foreign key (episodeId) references episodes(id)
);


-- insert data in users
insert into users (username, password, email, isAdmin) values ('admin', 'admin', 'admin@test.com', 1);
insert into users (username, password, email, isAdmin) values ('user', 'user', 'test@gmail.com', 0);



-- insert data in shows anime data
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Naruto', 'Naruto Uzumaki wants to be the best ninja in the land. Hes done well so far, but with the looming danger posed by the mysterious Akatsuki organization, Naruto knows he must train harder than ever and leaves his village for intense exercises that will push him to his limits.', 'https://gogocdn.net/images/anime/N/naruto.jpg', '2002', 'Action', 4);

-- insert data in seasons 
insert into seasons (showId, title, description, imageUrl, releaseDate) values (1, 'Season 1', 'Naruto Uzumaki wants to be the best ninja in the land. Hes done well so far, but with the looming danger posed by the mysterious Akatsuki organization, Naruto knows he must train harder than ever and leaves his village for intense exercises that will push him to his limits.', 'https://gogocdn.net/images/anime/N/naruto.jpg', '2002'),
(1, 'Season 2', 'Naruto Uzumaki wants to be the best ninja in the land. Hes done well so far, but with the looming danger posed by the mysterious Akatsuki organization, Naruto knows he must train harder than ever and leaves his village for intense exercises that will push him to his limits.', 'https://gogocdn.net/images/anime/N/naruto.jpg', '2003'),
(1, 'Season 3', 'Naruto Uzumaki wants to be the best ninja in the land. Hes done well so far, but with the looming danger posed by the mysterious Akatsuki organization, Naruto knows he must train harder than ever and leaves his village for intense exercises that will push him to his limits.', 'https://gogocdn.net/images/anime/N/naruto.jpg', '2004');

insert into episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) values 
(1, 'Enter: Naruto Uzumaki!', 1, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(1, 'My Name is Konohamaru!', 2, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTY=&title=Naruto+Episode+2&typesub=SUB'),
(1, 'Sasuke and Sakura: Friends or Foes?', 3, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTY=&title=Naruto+Episode+2&typesub=SUB'),
(2, 'Hospital Besieged: The Evil Hand Revealed!', 1, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUyNjA=&title=Naruto+Episode+58&typesub=SUB'),
(2, 'The Final Rounds: Rush to the Battle Arena!', 2, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUyNjA=&title=Naruto+Episode+59&typesub=SUB'),
(2, 'Byakugan vs. Shadow Clone Technique!', 3, 23, 'https://gogocdn.net/images/anime/N/naruto.jpg', 'https://gogohd.net/streaming.php?id=MjUyNjA=&title=Naruto+Episode+60&typesub=SUB');


-- insert data in comments sections
insert into comments (userId, episodeId, comment) values (1, 1, 'This is a comment');


insert into favorite (userId, showId) values (1, 1);

