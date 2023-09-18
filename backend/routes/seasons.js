const express = require('express');
const router = express.Router();
const request = require('../database/request');


router.get('/:showId', async (req, res) => {
    try {
        const showId = req.params.showId
        const resultat = await request.getSeasons(showId)

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