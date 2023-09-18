const express = require('express');
const router = express.Router();
const request = require('../database/request');

router.get('/', async (req, res) => {
    try {
        const resultat = await request.getShows()

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

router.get('/:id', async (req, res) => {
    try {
        const id = req.params.id
        const resultat = await request.getShowById(id)

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