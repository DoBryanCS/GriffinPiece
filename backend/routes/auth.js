const express = require('express');
const bcrypt = require('bcrypt');
const generateToken = require('../modules/token');
const knex = require('../database/auth');

const router = express.Router();

router.post('/login', async(req, res) => {
    res.header('Access-Control-Allow-Origin', '*');

    try {
        const { email, password } = req.body;

        const user = await knex.trouverUtilisateur(email);

        if (user == undefined) {
            return res.status(401).json({ succes: false, message: 'L\'utilisateur n\'existe pas' });
        }

        const passwordCorrect = await bcrypt.compare(password, user.password);

        if (!passwordCorrect) {
            return res.status(401).json({ succes: false, message: 'Le mot de passe est incorrect' });
        }

        const AUTH_TOKEN = generateToken.generateToken('1d', {
            id: user.id,
            username: user.username,
            isAdmin: user.isAdmin,
        });

        return res.status(200).json({
            succes: true,
            message: 'L\'utilisateur est connecté',
            token: AUTH_TOKEN,
            username: user.username
        });
    } catch (error) {
        res.status(500).json({ succes: false, message: error.message });
    }
});

router.post('/register', async(req, res) => {
    res.header('Access-Control-Allow-Origin', '*');

    try {
        const { email, password, username } = req.body;
        const passwordHash = await bcrypt.hash(password, 10);

        const user = await knex.trouverUtilisateur(email);
        if (user != undefined) {
            return res.status(409).json({ succes: false, message: 'Le email de l\'utilisateur existe deja' });
        };

        const userUsername = await knex.trouverUtilisateurUsername(username);
        if (userUsername != undefined) {
            return res.status(409).json({ succes: false, message: 'Le username existe deja' });
        };




        await knex.ajouterUtilisateur(email, passwordHash, username);

        return res.status(200).json({
            succes: true,
            message: 'L\'utilisateur est créé',
            email: email
        });
    } catch (error) {
        res.status(500).json({ succes: false, message: error.message });
    }
});


module.exports = router;