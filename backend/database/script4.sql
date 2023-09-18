-- use 5D1Equipe05;
drop table if exists episodes;
drop table if exists seasons;
drop table if exists shows;
drop table if exists users;
drop table if exists history;
drop table if exists favorite;
drop table if exists comments;

create table shows (
    id integer IDENTITY(1,1) PRIMARY KEY,
    title nvarchar(75) not null,
    description nvarchar(2000) not null,
    imageUrl nvarchar(500) not null,
    releaseDate nvarchar(20) not null,
    genre nvarchar(50) not null,
    rating integer not null
);


create table seasons (
    id integer IDENTITY(1,1) PRIMARY KEY,
    showId integer not null,
    title nvarchar(75) not null,
    description nvarchar(2000) not null,
    imageUrl nvarchar(500) not null,
    releaseDate nvarchar(20) not null,
    foreign key (showId) references shows(id)
);


create table episodes (
    id integer IDENTITY(1,1) PRIMARY KEY,
    seasonId integer not null,
    title nvarchar(75) not null,
    episodeNumber integer not null,
    length integer,
    imageUrl nvarchar(500) not null,
    videoUrl nvarchar(500) not null,
    foreign key (seasonId) references seasons(id)
);

create table users (
    id integer IDENTITY(1,1) PRIMARY KEY,
    username nvarchar(50) not null,
    password nvarchar(255) not null,
    email nvarchar(50) not null,
    isAdmin integer not null
);

create table history (
    id integer IDENTITY(1,1) PRIMARY KEY,
    userId integer not null,
    episodeId integer not null,
    date nvarchar(50) not null,
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
    id integer IDENTITY(1,1) PRIMARY KEY,
    userId integer not null,
    episodeId integer not null,
    comment nvarchar(255) not null,
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


insert into history (userId, episodeId, date) values (1, 2, '2022-10-04T17:45:31.580Z');


-- MATHIEU
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Cyberpunk: Edgerunners', 'In a dystopia overrun by corruption, crime, and cybernetic implants, an impulsive but talented street kid, after losing everything he has in a drive-by shooting, makes the choice to survive on the wrong side of the law as an edgerunner; a high-tech, black-market mercenary also known as a "cyberpunk".', 'https://upload.wikimedia.org/wikipedia/en/thumb/a/a1/Cyberpunk_Edgerunners_poster.jpg/250px-Cyberpunk_Edgerunners_poster.jpg', '2022', 'Action', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Made in Abyss', 'An orphaned girl named Riko lives in the Belchero Orphanage in the town of Orth. The town surrounds a strange, giant hole descending deep into the earth, which is known as the Abyss. The Abyss harbors artifacts and remnants of civilizations long gone, and is, therefore, a popular hunting spot for so-called Cave Raiders, who undertake arduous and dangerous descents into the mist-filled pit to recover whatever relics they can find. Returning from the Abyss can be dangerous as "the Curse of the Abyss," a mysterious and potentially fatal malady, manifests upon ascension. The deeper one goes, the more acute the effects of the curseelcome him into their close-knit group. Sometime later, a number of findings are made from the depths of the Abyss, including Lyza’s White Whistle and pages of discoveries and observations she had made, as well as a message presumably for Riko, stating she is waiting at the bottom of the Abyss. Riko, determined to find her mother, bids farewell to her friends and secretly departs into the Abyss with Reg as her companion.', 'https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Made_in_Abyss_volume_1_cover.jpg/220px-Made_in_Abyss_volume_1_cover.jpg', '2017', 'Dark Fantasy', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Dororo (2019)', 'During the Sengoku period, a feudal lord has his newborn son s organs and limbs sacrificed to the twelve demons gods in exchange of prosperity for his people. The baby is abandoned but found by the doctor Jukai who specializes in giving people prosthetic limbs. Jukai adopts the baby and gives him limbs. When the child grows up, he is attacked by demons and develops the ability of seeing their silhouette to fight back. After defeating his first enemy, the now teenager regains his ability of feeling pain. Jukai names the teenager Hyakkimaru and sends him off on journey to regain his body. Along the way, Hyakkimaru meets a child named Dororo who decides to follow him, teaching him how to live and fight demons in exchange for food when visiting villages.', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/43/DororoBluraycollection.png/220px-DororoBluraycollection.png', '2019', 'Action', 5);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Vinland Saga', 'Thorfinn pursues a journey with his father''s killer in order to take revenge and end his life in a duel as an honorable warrior and pay his father a homage. A young man named Thorfinn finds himself in a quest for revenge against his father''s killer.', 'https://upload.wikimedia.org/wikipedia/en/5/51/Vinland_Saga_volume_01_cover.jpg', '2019 ', 'Historical', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('High School DXD', 'Kuoh Academy (駒王学園, Kuō Gakuen, alt. Kuou Academy) is a former all-girls school that has recently turned co-ed, but it has a secret. Unknown to normal humans, angels (天使, Tenshi), fallen angels (堕天使, Datenshi) and devils (悪魔, Akuma) comprise part of the student population. One of these students, Issei Hyodo, is a lecherous second-year human student who lives a peaceful life. After an ordinary school day, Issei is suddenly asked out on a date by a girl named Yuma Amano. After their date, Yuuma brings Issei to a local park and makes a startling request that she wants him to die for her. She reveals herself as Raynare, a fallen angel, and she tries to kill him. Using her summoning card, Rias Gremory, a buxom third-year student at Kuoh Academy, revives him. Issei wakes up the next morning, thinking that the events that occurred were all just a dream. Immediately after being attacked by another fallen angel and waking up, he notices Rias naked in his room. Rias reveals to Issei her true identity as a devil and says that as a result of his death at the hands of Yuuma, she has reincarnated him as a devil, making him her faithful servant in the process.', 'https://upload.wikimedia.org/wikipedia/en/thumb/e/e2/High_School_DxD_Volume_1.png/220px-High_School_DxD_Volume_1.png', '2012', 'Supernatural', 4);

insert into seasons (showId, title, description, imageUrl,releaseDate)
values
(2, 'Season 1', 'In a dystopia overrun by corruption, crime, and cybernetic implants, an impulsive but talented street kid, after losing everything he has in a drive-by shooting, makes the choice to survive on the wrong side of the law as an edgerunner; a high-tech, black-market mercenary also known as a "cyberpunk".', 'https://static.bunnycdn.ru/i/cache/images/5/59/5985eb9f3b64109c155714b911e2a673.jpg', '2022'),
(3, 'Season 1', 'The Abyss—a gaping chasm stretching down into the depths of the earth, filled with mysterious creatures and relics from a time long past. How did it come to be? What lies at the bottom? Countless brave individuals, known as Divers, have sought to solve these mysteries of the Abyss, fearlessly descending into its darkest realms. The best and bravest of the Divers, the White Whistles, are hailed as legends by those who remain on the surface.Riko, daughter of the missing White Whistle Lyza the Annihilator, aspires to become like her mother and explore the furthest reaches of the Abyss.', 'https://static.bunnycdn.ru/i/cache/images/2018/04/aa6695b1aed42b438c81320a076db881.jpg', '2017'),
(3, 'Made in Abyss: The Golden City of the Scorching Sun', 'After surviving the brutal challenges of Idofront, Riko now possesses a White Whistle, allowing her to descend into the Abyss''s sixth layer—The Capital of the Unreturned.', 'https://static.bunnycdn.ru/i/cache/images/e/e4/e4da48a2e72a4e93cbf0820506c9a4c4.jpg','2022'),
(4, 'Season 1', 'The greedy samurai lord Daigo Kagemitsus land is dying, and he would do anything for power, even renounce Buddha and make a pact with demons.', 'https://static.bunnycdn.ru/i/cache/images/2019/02/eb223c6c91fee81b777434c8b63a0e69.jpg', '2019'),
(5, 'Season 1','Young Thorfinn grew up listening to the stories of old sailors that had traveled the ocean and reached the place of legend, Vinland. Its said to be warm and fertile, a place where there would be no need for fighting—not at all like the frozen village in Iceland where he was born, and certainly not like his current life as a mercenary.', 'https://static.bunnycdn.ru/i/cache/images/2019/07/cb5e9f82c036777efb6baf736fa61243.jpg', '2019'),
(6, 'Season 1', 'High school student Issei Hyoudou is your run-of-the-mill pervert who does nothing productive with his life, peeping on women and dreaming of having his own harem one day. Things seem to be looking up for Issei when a beautiful girl asks him out on a date, although she turns out to be a fallen angel who brutally kills him!', 'https://static.bunnycdn.ru/i/cache/images/2018/04/49c1bbfbf87147d1c108291e0af07b8d.jpg', '2012'),
(6, 'High School DXD New', 'The misadventures of Issei Hyoudou, high school pervert and aspiring Harem King, continue on in High School DxD New. As the members of the Occult Research Club carry out their regular activities, it becomes increasingly obvious that there is something wrong with their Knight, the usually composed and alert Yuuto Kiba.', 'https://static.bunnycdn.ru/i/cache/images/2018/04/4e8622512caa0cb3450f51046c7438f2.jpg', '2013'),
(6, 'High School DXD Born', 'The Red Dragon Emperor, Issei Hyoudou, and the Occult Research Club are back in action as summer break comes for the students of Kuoh Academy. After their fight with Isseis sworn enemy, Vali and the Chaos Brigade, it is clear just how inexperienced Rias Gremorys team is.', 'https://static.bunnycdn.ru/i/cache/images/2018/04/a486b520143f13e14f2a81015dd29f0f.jpg','2015'),
(6, 'High School DXD Hero', 'After rescuing his master, Rias Gremory, from the Dimensional Gap, Red Dragon Emperor and aspiring Harem King Issei Hyoudou can finally return to his high school activities alongside fellow members of the Occult Research Club: Yuuto Kiba, Asia Argento, Xenovia Quarta, and Irina Shidou.', 'https://static.bunnycdn.ru/i/cache/images/2018/04/edf05babdf70b0ffef897d2cd530c9f1.jpg', '2018');


-- Cyberpunk Queries
INSERT INTO episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) VALUES 
(4, 'Let You Down', 1, 23, 'https://wallpapercave.com/wp/wp11495496.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(4, 'Like A Boy', 2, 23, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYFDh4GCwYXrq_Oim0w7y-cv4w6SyxJreSVA&usqp=CAU', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(4, 'Smooth Criminal', 3, 23, 'https://www.svg.com/img/gallery/things-are-getting-worse-for-cyberpunk-2077/intro-1607611901.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB' ),
(4, 'Lucky You', 4, 23, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbTaVLpXapRdpCnulxI3XWONO2fRjfQ8SBbw&usqp=CAU','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB')



-- Made in Abyss
INSERT INTO episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) VALUES 
(5,'The City of the Great Pit', 1, 23, 'https://cdn.myanimelist.net/images/anime/1864/122519.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(5,'Resurrection Festival', 2, 23, 'https://images.randomhouse.com/cover/9781638587170', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(5,'Departure', 3, 23, 'https://manga.tokyo/wp-content/uploads/2017/08/MADE-IN-ABYSS-2.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(5,'The Edge of the Abyss', 4, 23, 'https://www.themoviedb.org/t/p/w500/fsPZdpocYYbvDS2YEE1Xohqo1ni.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(6,'The Compass Pointed to the Darkness', 1, 23, 'https://cdn.myanimelist.net/images/characters/10/350283.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(6,'Capital of the Unreturned', 2, 23, 'https://preview.redd.it/tpt9esmczd251.jpg?auto=webp&s=02880d242df5335a3af2bb0a3bd816d1fd338072', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(6,'Village of the Hollows', 3, 23, 'https://admintoytopia.toytopia.co.th/data/img/shop1/product/product61091_800.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(6,'Friend', 4, 23, 'https://i.pinimg.com/236x/7b/33/40/7b334035e1125ede035a2f85a4349eeb.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB')



-- Dororo (2019)
INSERT INTO episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) VALUES 
(7, 'The Story of Daigo', 1, 23, 'https://static.wikia.nocookie.net/dororo/images/0/0c/Dororo_anime_design_2019.png/revision/latest?cb=20190126180301','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(7, 'The Story of Bandai', 2, 23, 'https://honeysanime.com/wp-content/uploads/2019/05/dororo-dvd-300x424.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(7, 'The Story of Jukai', 3, 23, 'https://track2traininginstitute.files.wordpress.com/2022/02/dororo-lyrics.jpg?w=460','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(7, 'The Story of the Cursed Sword', 4, 23, 'https://cdn.shopify.com/s/files/1/0038/1245/8565/products/5b214c48686b0a8b6bed516af02bd71b_906da744-5a56-48ee-8bef-f270fc62f710_300x.jpg?v=1655944905','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB')

-- Vinland Saga
INSERT INTO episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) VALUES 
(8,'Somewhere Not Here', 1, 23, 'https://cdn.myanimelist.net/images/anime/1500/103005.jpg', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(8,'Sword', 2, 23, 'https://static.wikia.nocookie.net/villains/images/3/3c/Askel.png/revision/latest?cb=20201115175755', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(8,'Troll', 3, 23, 'https://upload.wikimedia.org/wikipedia/en/thumb/7/79/Thorfinnnvolumefirst.png/220px-Thorfinnnvolumefirst.png', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(8,'A True Warrior', 4, 23, 'https://static.wikia.nocookie.net/vinlandsaga/images/a/ab/Thors_anime_design.png/revision/latest/scale-to-width-down/250?cb=20190525082630', 'https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB')


-- HighSchool DxD
INSERT INTO episodes (seasonId, title, episodeNumber, length, imageUrl, videoUrl) VALUES 
(9,'I Got a Girlfriend!', 1, 23, 'https://www.themoviedb.org/t/p/original/254UOvzBHnue5HA26FpfNalCszd.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(9,'I Quit Being Human!', 2, 23, 'https://images2.vudu.com/poster2/478985-338','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(9,'I Got a Friend!', 3, 23, 'https://i1.sndcdn.com/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(9,'I Am Saving My Friend!', 4, 23, 'https://cdn2.ezcosplay.com/media/catalog/product/2/7/2785340_1630635618_1.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(10,'Another Disquieting Premonition!', 1, 23, 'https://static.displate.com/280x392/displate/2020-03-23/8493fc330a9e687ab927c8d8293a1297_d1dcac5538f15ba4b9da5e9eaa124f0c.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(10,'The Holy Sword Is Here!', 2, 23, 'https://cdn.myanimelist.net/images/anime/1331/111940.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(10,'I Will Destroy the Holy Sword!', 3, 23, 'https://animefigureszone.com/wp-content/uploads/2020/05/High-School-DxD-HERO-Akeno-Himejima-Scale-Figure-001.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(10,'A Strong Enemy Appeared!', 4, 23, 'https://static.wikia.nocookie.net/among-us-wiki/images/3/31/Red.png/revision/latest?cb=20211122214947','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(11,'Summer Break! Off to the Underworld!', 1, 23, 'https://i.imgflip.com/62mqzr.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(11,'Young Devils Gather', 2, 23, 'https://i.imgflip.com/5op65z.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(11,'Cat and Dragon', 3, 23, 'https://i.imgflip.com/6ovxnp.jpg','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(11,'Interception, Commence!', 4, 23, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqrEi8vXOoFk7HomV3wi3TW8fV2fVtOp9n2Q&usqp=CAU','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(12,'Holiness Behind the Gym', 1, 23, 'https://preview.redd.it/u3q5x41167i11.png?width=640&crop=smart&auto=webp&s=a3d4b7e3089d966b4ba577e88a1a9aa7999e33cd','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(12,'That is Right, Lets go to Kyoto', 2, 23, 'https://w7.pngwing.com/pngs/157/238/png-transparent-guts-griffith-casca-berserk-anime-others-manga-fictional-character-meme.png','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(12,'School Trip, an Abrupt Attack', 3, 23, 'https://tse1.explicit.bing.net/th?id=OVP.pSjWGvqQYUAP13ul5tVnYgHgFo&w=268&h=150&c=7&rs=1&qlt=90&o=5&dpr=1.25&pid=2.1','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB'),
(12,'The Party of Heroes', 4, 23, 'https://hips.hearstapps.com/digitalspyuk.cdnds.net/18/21/1527176624-screen-shot-2018-05-24-at-164005.jpg?resize=480:*','https://gogohd.net/streaming.php?id=MjUwNTQ=&title=Naruto+Episode+1&typesub=SUB')


-- KHOA
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Rent a girlfriend', 'After being dumped by his girlfriend, a college student enlists a dating service to "rent" a girlfriend who, unbeknownst to him, happens to be both a fellow student and his neighbor.', 'https://m.media-amazon.com/images/M/MV5BMmFlNGVkYmUtOGJiZi00NjM3LTk4NGEtNGJiNGVhNWQ5NWIxXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg', '2020', 'Comedy', 3);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Hunter X Hunter', 'Gon Freecss aspires to become a Hunter, an exceptional being capable of greatness. With his friends and his potential, he seeks out his father, who left him when he was younger.', 'https://m.media-amazon.com/images/M/MV5BNGM0YTk3MWEtN2JlZC00ZmZmLWIwMDktZTMxZGE5Zjc2MGExXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UX1000_.jpg', '2011', 'Action', 5);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('One piece', 'Follows the adventures of Monkey D. Luffy and his pirate crew in order to find the greatest treasure ever left by the legendary Pirate, Gold Roger. The famous mystery treasure named "One Piece".', 'https://m.media-amazon.com/images/M/MV5BODcwNWE3OTMtMDc3MS00NDFjLWE1OTAtNDU3NjgxODMxY2UyXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg', '1999', 'Adventure', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Redo of healer', 'Keyaru is a healing magician who is sexually abused repeatedly by others. Using his healing magic, he goes back four years into the past to get revenge on the ones who abused him.', 'https://m.media-amazon.com/images/M/MV5BZDg5OTgzNTEtMDJmOS00MWMzLWFmYmQtNTNlZjg5YjI1YjRiXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg', '2020', 'Action', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Tower of god', 'Reach the top, and everything will be yours. At the top of the tower exists everything in this world, and all of it can be yours.', 'https://m.media-amazon.com/images/M/MV5BZGM4NjE1OWYtNzcwMC00ZGY0LWE4NjEtZTgzYzY4YWU5M2E3XkEyXkFqcGdeQXVyMzI2Mjc1NjQ@._V1_FMjpg_UX1000_.jpg', '2021', 'Action', 2);

insert into seasons (showId, title, description, imageUrl,releaseDate)
values
(7, 'Rent a girlfriend season 1', 'After being dumped by his girlfriend, a college student enlists a dating service to "rent" a girlfriend who, unbeknownst to him, happens to be both a fellow student and his neighbor.', 'https://gogoanime.run/images/625ddd823832ef0015c4b0e3.png', '2020'),
(7, 'Rent a girlfriend season 2', 'Second season of Kanojo, Okarishimasu.', 'https://gogoanime.run/images/625ddd8d3832ef0015c4b0ef.png', '2022'),
(8, 'Hunter x Hunter season 1', 'Gon Freecss aspires to become a Hunter, an exceptional being capable of greatness. With his friends and his potential, he seeks out his father, who left him when he was younger.', 'https://gogoanime.run/images/625d7f1c3537ea0015362c9d.jpg','2011'),
(8, 'Hunter x Hunter season 2', 'Second season of Hunter X Hunter.', 'https://gogoanime.run/images/625d7f1c3537ea0015362c9d.jpg','2011'),
(9, 'One Piece season 1', 'One Piece: East Blue', 'https://gogocdn.net/images/anime/One-piece.jpg', '1999'),
(9, 'One Piece season 2', 'One Piece: Alabasta', 'https://gogocdn.net/images/anime/One-piece.jpg', '2000'),
(9, 'One Piece season 3', 'One Piece: Sky Island', 'https://gogocdn.net/images/anime/One-piece.jpg', '2001'),
(10, 'Redo of healer', 'Keyaru is a healing magician who is sexually abused repeatedly by others. Using his healing magic, he goes back four years into the past to get revenge on the ones who abused him.', 'https://m.media-amazon.com/images/M/MV5BZDg5OTgzNTEtMDJmOS00MWMzLWFmYmQtNTNlZjg5YjI1YjRiXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg', '2021'),
(11, 'Tower of god', 'Reach the top, and everything will be yours. At the top of the tower exists everything in this world, and all of it can be yours.', 'https://gogocdn.net/cover/kami-no-tou.png', '2020');



-- JAD
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Mahou Shoujo Madoka★Magica', 'Madoka Kaname and Sayaka Miki are regular middle school girls with regular lives, but all that changes when they encounter Kyuubey, a cat-like magical familiar, and Homura Akemi, the new transfer student Kyuubey offers them a proposition: he will grant any one of their wishes and in exchange, they will each become a magical girl, gaining enough power to fulfill their dreams. However, Homura Akemi, a magical girl herself, urges them not to accept the offer, stating that everything is not what it seems. A story of hope, despair, and friendship, Mahou Shoujo Madoka★Magica deals with the difficulties of being a magical girl and the price one has to pay to make a dream come true.', 'https://cdn.myanimelist.net/images/anime/11/55225.jpg', '2011' , 'Drama', 5);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Mars Of destruction', 'Several months after a probe returning from Mars burns up during reentry, strange creatures dubbed "Ancients" begin to appear throughout Tokyo. Aggressive and dangerous, they cannot be killed with ordinary weapons. As scientists struggle to find the cause of their sudden appearance, the monsters wreak havoc across the world.', 'https://cdn.myanimelist.net/images/anime/7/68855.jpg', '2005', 'Action', 1);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Angel Beats!' , 'Death is one of many mysteries that has left humanity in the dark since the dawn of time. However, the burning question of what happens to the soul after one dies is soon answered to 17-year-old Yuzuru Otonashi. Waking up with no previous memories in a dimension between life and death, he discovers the unsettling truth of the afterlife.Taking the form of a high school, this bizarre dimension is designated to shelter those who died unwanted deaths. Feeling wronged by God during their earthly lives, the schools residents have decided to form the Afterlife Battlefront—a rebellious faction determined to oppose their god-like student council president, Kanade "Angel" Tachibana. The groups leader, Yuri Nakamura, recruits Otonashi in their fight against Angel in order to take control of their own lives. However, questioning the morality behind their actions, Otonashi takes a step behind the enemy lines to understand the opposing side of their common fate.' , 'https://cdn.myanimelist.net/images/anime/1244/111115.jpg' , '2005', 'Drama', 4);
insert into shows (title, description, imageUrl, releaseDate, genre, rating) values ('Azur lane', 'When the Sirens, an alien force with an arsenal far surpassing the limits of current technology, suddenly appeared, a divided humanity stood in complete solidarity for the first time. Four countries—Eagle Union, Royal Navy, Sakura Empire, and Iron Blood—formed Azur Lane, paving the way for the improvement of modern warfare, which led to an initial victory against the common threat. However, this tenuous union was threatened by opposing ideals, dividing the alliance into two. Sakura Empire and Iron Blood broke away and formed the Red Axis, and humanity became fragmented once again. As a seasoned and experienced fighter, the Grey Ghost Enterprise shoulders Azur Lanes hope for ending the war. But behind her stoic persona hides a frail girl, afraid of the ocean. Even so, she continues to fight as she believes that its the only purpose for her existence.' ,'https://cdn.myanimelist.net/images/anime/1106/111620.jpg', '2020' , 'Action', 0);

insert into seasons (showId, title, description, imageUrl,releaseDate)
values
(12, 'Mahou Shoujo Madoka★Magica', 'A story of hope, despair, and friendship, Mahou Shoujo Madoka★Magica deals with the difficulties of being a magical girl and the price one has to pay to make a dream come true.' , 'https://cdn.myanimelist.net/images/anime/11/55225.jpg', '2011'),
(12, 'Mahou Shoujo Madoka★Magica Movie 3: Hangyaku no Monogatari', 'The young girls of Mitakihara happily live their lives, occasionally fighting off evil, but otherwise going about their peaceful, everyday routines. However, Homura Akemi feels that something is wrong with this unusually pleasant atmosphere—though the others remain oblivious, she cant help but suspect that there is more to what is going on than meets the eye: someone who should not exist is currently present to join in on their activities.', 'https://cdn.myanimelist.net/images/anime/11/55225.jpg', 2013),
(13, 'Season 1', 'Several months after a probe returning from Mars burns up during reentry, strange creatures dubbed "Ancients" begin to appear throughout Tokyo. Aggressive and dangerous, they cannot be killed with ordinary weapons. As scientists struggle to find the cause of their sudden appearance, the monsters wreak havoc across the world.', 'https://cdn.myanimelist.net/images/anime/7/68855.jpg', '2005'),
(14, 'Season 1' ,'Death is one of many mysteries that has left humanity in the dark since the dawn of time. However, the burning question of what happens to the soul after one dies is soon answered to 17-year-old Yuzuru Otonashi. Waking up with no previous memories in a dimension between life and death, he discovers the unsettling truth of the afterlife.', 'https://cdn.myanimelist.net/images/anime/1244/111115.jpg' , '2005'),
(14, 'Specials' , 'As the Shinda Sekai Sensen (SSS) continue their vindictive rebellion against God, their leader, Yuri Nakamura, comes up with an ingenious plan to escape the afterlife' , 'https://cdn.myanimelist.net/images/anime/4/25073.jpg' , '2010'),
(15 , 'Azur lane the animation' , 'When the "Sirens," an alien force with an arsenal far surpassing the limits of current technology, suddenly appeared, a divided humanity stood in complete solidarity for the first time. Four countries—Eagle Union, Royal Navy, Sakura Empire, and Iron Blood—formed Azur Lane, paving the way for the improvement of modern warfare, which led to an initial victory against the common threat. However, this tenuous union was threatened by opposing ideals, dividing the alliance into two. Sakura Empire and Iron Blood broke away and formed the Red Axis, and humanity became fragmented once again.' , 'https://cdn.myanimelist.net/images/anime/1106/111620.jpg', '2020'),
(15, 'Azur Lane: Slow Ahead!' , 'In a seemingly peaceful time without looming threats of Sirens or war, shipgirls from different nations live together in harmony.' , 'https://cdn.myanimelist.net/images/anime/1137/113872.jpg' , '2021');
