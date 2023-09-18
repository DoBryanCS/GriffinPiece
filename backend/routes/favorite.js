const express = require('express');
const router = express.Router();
const request = require('../database/request');

router.get('/:id', async (req, res) => {
    try {
        const userId = req.infoUser.id
        const showId = req.params.id
        // favorite = {userId: userId, showId: showId}
        let favorite = await request.getFavorite(userId, showId)
        
        if(favorite == undefined){
            res.json({
                isFavorite: false
            })
        } else {
            res.json({
                isFavorite: true
            })
        }        
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});


router.post('/:id', async (req, res) => {
    try {
        const userId = req.infoUser.id
        const showId = req.params.id

        let isFavorite = await request.getFavorite(userId, showId)
        
        if(isFavorite == undefined){
            await request.addFavorite(userId, showId)
            res.json({  
                success: true
            })
        } else {
            res.json({
                success: false,
                message: 'This show is already in your favorites'
            })
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

router.delete('/:id', async (req, res) => {
    try {
        const userId = req.infoUser.id
        const showId = req.params.id

        let isFavorite = await request.getFavorite(userId, showId)

        if(isFavorite != undefined){
            await request.deleteFavorite(userId, showId)
            res.json({
                success: true
            })
        } else {
            res.json({
                success: false,
                message: 'This show is not in your favorites'
            })
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});
module.exports = router;