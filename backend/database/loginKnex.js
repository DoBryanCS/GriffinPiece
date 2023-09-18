const loginKnex = {
    client: 'mssql',
    connection: {
        host: 'sv55.cmaisonneuve.qc.ca',
        user: '5D1Equipe05',
        password: 'hPYu5rTsLFaaXttAwDybp1DAKcKin9',
        database: '5D1Equipe05',
        options: {
            enableArithAbort: false,
        },
    },
    pool: { min: 0, max: 7 },
    useNullAsDefault: true,
};

module.exports = loginKnex;
