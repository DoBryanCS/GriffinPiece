const jwt = require('jsonwebtoken');

function generateToken(expiration, contentJson) {
    const AUTH_TOKEN = jwt.sign(
        contentJson,
        "WhiteBeardIsTheBestPirateEverAndHeIsSexy",
        { expiresIn: expiration },
    );

    return AUTH_TOKEN;
}

module.exports = {
    generateToken,
};
