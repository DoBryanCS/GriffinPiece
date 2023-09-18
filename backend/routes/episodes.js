const express = require('express');
const router = express.Router();
const request = require('../database/request');

router.get('/:seasonId', async (req, res) => {
    try {
        const seasonId = req.params.seasonId
        const resultat = await request.getEpisodes(seasonId)

        if(resultat.length!=0)
		{   
			res.send(resultat);
		} else {
			res.send({result : 'error'});
		}
	} catch (error) {
		res.status(500).json(error.message);
	}
});

module.exports = router;