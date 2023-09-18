const jwt = require('jsonwebtoken');
const request = require('../database/request');
require('dotenv').config();

function authMiddleware(req, res, next) {
    const authHeaders = req.headers.authorization;
    const token = authHeaders && authHeaders.split(' ')[1];

    if (token == null) return res.status(401).json({ succes: false, message: 'Le token est null'});

    jwt.verify(token, process.env.SECRET_KEY, (error, infoUser) => {
        if (error) return res.status(403).json({ succes: false, message: 'Le token pris en compte n\'est pas valide' });

        // console.log(`${infoUser.id}`)

        req.infoUser = infoUser;


        next();
    });
}

module.exports = authMiddleware;
