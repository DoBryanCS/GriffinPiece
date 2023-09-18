const knexModule = require('knex');
const loginKnex = require('./loginKnex');

const knex = knexModule(loginKnex);

// Requete de test
async function getUtilisateursAll() { return await knex('users'); };

async function trouverUtilisateur(email) { return await knex('users').where('email', email).first(); }

async function trouverUtilisateurUsername(username) { return await knex('users').where('username', username).first(); }

async function ajouterUtilisateur(email, password, username) {
    return await knex('users').insert({ email, password, username, isAdmin: 0 });
}

async function trouverUtilisateurId(id) {
    return await knex('users').where('id', id).first();
}

module.exports = {
    getUtilisateursAll,
    trouverUtilisateur,
    trouverUtilisateurUsername,
    ajouterUtilisateur,
    trouverUtilisateurId
};