const express = require('express');
require('dotenv').config();


const apiJSON = require('./Documentation/swagger');
const swaggerUi = require('swagger-ui-express');

const app = express();
const cors = require('cors');


const PORT = process.env.PORT;

const authRouteur = require('./routes/auth');
const showRouteur = require('./routes/show');
const seasonsRouteur = require('./routes/seasons');
const seasonRouteur = require('./routes/season')
const episodeRouteur = require('./routes/episode');
const episodesRouteur = require('./routes/episodes');
const historyRouteur = require('./routes/history');
const commentsRouteur = require('./routes/comments');
const userRouteur = require('./routes/user');
const favoriteRouteur = require('./routes/favorite');
const favoritesRouteur = require('./routes/favorites');

const authMiddleware = require('./modules/authMiddleware');


app.use(cors());
app.use(express.json());

app.use('/auth', authRouteur);
app.use('/show', showRouteur);
app.use('/season', seasonRouteur);
app.use('/seasons', seasonsRouteur);
app.use('/episode', authMiddleware, episodeRouteur);
app.use('/episodes', episodesRouteur);
app.use('/history', authMiddleware, historyRouteur);
app.use('/comments', commentsRouteur);
app.use('/user', authMiddleware, userRouteur)
app.use('/favorite', authMiddleware, favoriteRouteur)
app.use('/favorites', authMiddleware, favoritesRouteur)

app.use('/insomnia', function(req, res) {
    res.sendFile(__dirname + '/Documentation/insomnia.json');
});


app.use("/", swaggerUi.serve, swaggerUi.setup(apiJSON));

app.listen(PORT, () => {
    console.log(`Mon application roule sur http://localhost:${PORT}`);
});
