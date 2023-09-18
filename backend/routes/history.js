const express = require('express');
const router = express.Router();
const request = require('../database/request');


router.get('/', async (req, res) => {
    try {
        const userId = req.infoUser.id;
        const resultat = await request.getHistory(userId)

        res.send(resultat);

	} catch (error) {
		res.status(500).json(error.message);
	}
});

module.exports = router;