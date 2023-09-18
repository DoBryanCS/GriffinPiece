const express = require('express');
const router = express.Router();
const request = require('../database/request');

router.get('/', async (req, res) => {
    try {
        const userId = req.infoUser.id
        
        let favorite = await request.getFavorites(userId)
        
        if(favorite == undefined){
            res.json([]);
        } else {
            res.json(favorite);
        }        
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});



module.exports = router;