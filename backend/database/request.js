const knexModule = require('knex');
const loginKnex = require('./loginKnex');
const knex = knexModule(loginKnex);


// SHOWS
function getShowById(id) {
    return knex('shows').where('id', id).first();
}

function getShows() {
    return knex('shows');
}

// SEASONS
function getSeasons(showId) {
    return knex('seasons')
    .where('showId', showId)
    .join('shows', 'seasons.showId', '=', 'shows.id')
    .select('seasons.id', 'seasons.showId', 'seasons.title', 'seasons.description', 'seasons.imageUrl', 'seasons.releaseDate', 'shows.title as showTitle');
}

function getSeason(id) {
    return knex('seasons')
    .where('seasons.id', id)
    .join('shows', 'seasons.showId', '=', 'shows.id')
    .select('seasons.id', 'seasons.showId', 'seasons.title', 'seasons.description', 'seasons.imageUrl', 'seasons.releaseDate', 'shows.title as showTitle')
    .first();
}

// EPISODES
function getEpisodes(seasonId) {
    return knex('episodes')
        .where('seasonId', seasonId)
        .join('seasons', 'episodes.seasonId', '=', 'seasons.id')
        .select('episodes.id', 'seasons.showId', 'episodes.title', 'episodeNumber', 'length', 'episodes.imageUrl');
}

function getEpisode(id) {
    return knex('episodes').where('episodes.id', id)
        .join('seasons', 'episodes.seasonId', '=', 'seasons.id')
        .select('episodes.id', 'episodes.seasonId', 'seasons.showId', 'episodes.title', 'episodeNumber', 'length', 'episodes.imageUrl', 'episodes.videoUrl')
        .first();
}

// COMMENTS
function getComments(id) {
    return knex('comments').where('episodeId', id);
}

// COMMENTS
function postComments(id) {
    return knex('comments').where('episodeId', id);
}

// HISTORY
function getHistory(userId) {
    return knex('history').where("userId", userId);
}

async function insertToHistory(userId, episodeId, date) {
    await knex('history').insert({
        userId: userId,
        episodeId: episodeId,
        date: date
    })
}

// USER

function getUser(id) {
    return knex('users').where('id', id).first();
}

async function modifyUserEmail(id, newEmail) {
    return await knex('users')
        .where('id', id)
        .update({ email: newEmail })
}

async function modifyUserPassword(id, newPassword) {
    return await knex('users')
        .where('id', id)
        .update({ password: newPassword })
}


// FAVORITES

async function getFavorites(userId) {
    // return shows that are joined with favorites
    return await knex('shows')
        .join('favorite', 'shows.id', '=', 'favorite.showId')
        .where('favorite.userId', userId)
        .select(
            'shows.id',
            'shows.title',
            'shows.imageUrl');
}

async function getFavorite(userId, showId) {
    return await knex('favorite').where('userId', userId).andWhere('showId', showId).first();
}

async function addFavorite(userId, showId) {
    return await knex('favorite').insert({
        userId: userId,
        showId: showId
    })
}

async function deleteFavorite(userId, showId) {
    return await knex('favorite')
        .where('userId', userId)
        .andWhere('showId', showId)
        .del();
}

module.exports = {
    getShowById,
    getShows,
    getSeason,
    getSeasons,
    getEpisode,
    getEpisodes,
    getComments,
    getHistory,
    insertToHistory,
    getUser,
    modifyUserEmail,
    modifyUserPassword,
    getFavorites,
    getFavorite,
    addFavorite,
    deleteFavorite
};