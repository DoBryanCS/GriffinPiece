const swaggerDocument = {
    openapi: '3.0.0',
    info: {
        title: 'Projet 3 API',
        description: 'Projet 3 API',
        contact: {
            name: 'Francis Jad Khoa Mathieu'
        },
        servers: ['http://localhost:3000']
    },
    tags: [
        { name: 'Authentification' },
        { name: 'User' },
        { name: 'Shows' },
        { name: 'Seasons' },
        { name: 'Episodes' },
        { name: 'Comments' },
        { name: 'History' },
        { name: 'Favorites' },
    ],
    paths: {
        "/auth/login": {
            "post": {
                tags: ["Authentification"],
                summary: "Get Token",
                description: "Authentification",
                parameters: [{
                    name: "login",
                    in: "body",
                    schema: {
                        type: "object",
                        properties: {
                            email: {
                                type: "string",
                                example: "e12345678@cmaisonneuve.qc.ca"
                            },
                            password: {
                                type: "string",
                                example: "password"
                            }
                        }
                    }
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/LoginResponse"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "object",
                                    properties: {
                                        succes: {
                                            type: "boolean",
                                            example: "false"
                                        },
                                        message: {
                                            type: "string",
                                            example: "email ou mot de passe incorrect!"
                                        }
                                    }
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }


            }
        },
        "/auth/register": {
            "post": {
                tags: ["Authentification"],
                summary: "Create User",
                description: "Authentification",
                parameters: [{
                    name: "register",
                    in: "body",
                    schema: {
                        type: "object",
                        properties: {
                            email: {
                                type: "string",
                                example: "e12345678@cmaisonneuve.qc.ca"
                            },
                            password: {
                                type: "string",
                                example: "password"
                            },
                            username: {
                                type: "string",
                                example: "username"
                            }
                        }
                    }
                }],
                responses: {
                    "201": {
                        description: "successful operation",
                        schema: {
                            type: "object",
                            properties: {
                                succes: {
                                    type: "boolean",
                                    example: "true"
                                },
                                token: {
                                    type: "string",
                                    example: "ABCTOKEN"
                                },
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }


            }
        },
        "/user": {
            "get": {
                tags: ['User'],
                summary: "get info about current User",
                description: "get info about current User",
                security: [{
                    bearerAuth: []
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/User"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        "/user/email": {
            "put": {
                tags: ['User'],
                summary: "Update current User",
                description: "Permet de update un user",
                security: [{
                    bearerAuth: []
                }],
                parameters: [{
                    name: "newData",
                    in: "body",
                    schema: {
                        type: "object",
                        properties: {
                            email: {
                                type: "string",
                                example: "e12345678@cmaisonneuve.qc.ca"
                            },
                        }
                    }
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/200"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        "/user/password": {
            "put": {
                tags: ['User'],
                summary: "Update current User",
                description: "Permet de update un user",
                security: [{
                    bearerAuth: []
                }],
                parameters: [{
                    name: "newData",
                    in: "body",
                    schema: {
                        type: "object",
                        properties: {
                            password: {
                                type: "string",
                                example: "testtesttest"
                            },
                        }
                    }
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/200"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        "/user/checkPassword": {
            "post": {
                tags: ['User'],
                summary: "check if password is correct",
                description: "Permet de check le password d'un user",
                security: [{
                    bearerAuth: []
                }],
                parameters: [{
                    name: "password",
                    in: "body",
                    schema: {
                        type: "object",
                        properties: {
                            password: {
                                type: "string",
                                example: "testtesttest"
                            },
                        }
                    }
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/200"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        // "/user/{Id}": {
        //     "delete": {
        //         tags: ['User'],
        //         summary: "Delete User from Id",
        //         description: "Permet de delete un user",
        //         security: [
        //             {
        //                 bearerAuth: []
        //             }
        //         ],
        //         parameters: [
        //             {
        //                 name: "Id",
        //                 in: "path",
        //                 description: "Le id du user",
        //                 required: true,
        //                 type: "int",
        //                 example: 3
        //             }
        //         ],
        //         responses: {
        //             "200": {
        //                 description: "successful operation",
        //                 schema: {
        //                     type: "object",
        //                     properties: {
        //                         success: {
        //                             type: "boolean",
        //                             example: "true"
        //                         },
        //                     }
        //                 }
        //             },
        //             "401": {
        //                 description: "Unauthorized",
        //                 schema: {
        //                     type: "object",
        //                     properties: {
        //                         succes: {
        //                             type: "boolean",
        //                             example: "false"
        //                         },
        //                         message: {
        //                             type: "string",
        //                             example: "Vous n'etes pas connecter!"
        //                         }
        //                     }
        //                 }
        //             },
        //             "403": {
        //                 description: "Forbidden",
        //                 schema: {
        //                     type: "object",
        //                     properties: {
        //                         succes: {
        //                             type: "boolean",
        //                             example: "false"
        //                         },
        //                         message: {
        //                             type: "string",
        //                             example: "Vous n'avez pas les droits nécessaires!"
        //                         }
        //                     }
        //                 }
        //             },
        //             "404": {
        //                 description: "Not Found",
        //                 schema: {
        //                     type: "object",
        //                     properties: {
        //                         succes: {
        //                             type: "boolean",
        //                             example: "false"
        //                         },
        //                         message: {
        //                             type: "string",
        //                             example: "User not found!"
        //                         }
        //                     }
        //                 }
        //             },
        //             "500": {
        //                 description: "Internal Server Error"
        //             }
        //         }
        //     }
        // },
        "/comment/{episodeId}": {
            "post": {
                tags: ['Comments'],
                summary: "Create Comment",
                description: "Permet de creer un commentaire",
                security: [{
                    bearerAuth: []
                }],
                parameters: [{
                        name: "episodeId",
                        in: "path",
                        description: "Le id de l'episode",
                        required: true,
                        type: "int",
                        example: 3
                    },
                    {
                        name: "comment",
                        in: "body",
                        schema: {
                            type: "object",
                            properties: {
                                comment: {
                                    type: "string",
                                    example: "Among us Fong us One Piece"
                                }
                            }
                        }
                    },
                ],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "object",
                                    properties: {
                                        success: {
                                            type: "boolean",
                                            example: "true"
                                        },
                                    }
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
            "get": {
                tags: ['Comments'],
                summary: "Get all Comment from episodeId",
                description: "Permet de load des commentaire de un episode",
                parameters: [{
                    name: "episodeId",
                    in: "path",
                    description: "Le id de l'episode",
                    required: true,
                    type: "int",
                    example: 3
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/Comment"
                                    }
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        "/show/{id}": {
            "get": {
                tags: ["Shows"],
                summary: "Get One show using it's ID",
                description: "Allows to get the info of a show",
                parameters: [{
                    name: "Id",
                    in: "path",
                    description: "Le id du show",
                    required: true,
                    type: "int",
                    example: 7
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/Show"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/show": {
            "get": {
                tags: ["Shows"],
                summary: "Get all shows",
                description: "Allows to get every show",
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/Show"
                                    }
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/season/{seasonId}": {
            "get": {
                tags: ["Seasons"],
                summary: "Get season info",
                description: "Allows to get the info of a season",
                parameters: [{
                    name: "seasonId",
                    in: "path",
                    description: "Le id de la season",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/Season"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/seasons/{showId}": {
            "get": {
                tags: ["Seasons"],
                summary: "Get seasons",
                description: "Allows to get all seasons of a show",
                parameters: [{
                    name: "showId",
                    in: "path",
                    description: "Le id du show",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/Season"
                                    }
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/episode/{episodeId}": {
            "get": {
                tags: ["Episodes"],
                summary: "Get episode from id",
                description: "Allows to get the detail of an episode from episodeId",
                security: [{
                    bearerAuth: []
                }],
                parameters: [{
                    name: "episodeId",
                    in: "path",
                    description: "Le id de l'episode",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/EpisodeDetail"
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        schema: {
                            type: "object",
                            properties: {
                                succes: {
                                    type: "boolean",
                                    example: "false"
                                },
                                message: {
                                    type: "string",
                                    example: "Vous deviez vous connecter"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/episodes/{seasonId}": {
            "get": {
                tags: ["Episodes"],
                summary: "Get episode from season id",
                description: "Allows to get episodes from the season id",
                parameters: [{
                    name: "episodeId",
                    in: "path",
                    description: "Le id de l'episode",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/EpisodeShort"
                                    }
                                }
                            }
                        }
                    },
                    "404": {
                        description: "Not Found",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/404"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/history": {
            "get": {
                tags: ["History"],
                summary: "Get history",
                description: "Get the history of the user",
                security: [{
                    bearerAuth: []
                }, ],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/History"
                                    },
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            }
        },
        "/favorite": {
            "get": {
                tags: ["Favorite"],
                summary: "Get if show is favorite",
                description: "Get if the user has added the show to his favorite",
                security: [{
                    bearerAuth: []
                }, ],
                parameters: [{
                    name: "showId",
                    in: "query",
                    description: "Le id du show",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "object",
                                    properties: {
                                        isFavorite: {
                                            type: "boolean",
                                            example: "true"
                                        }
                                    }
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
            "post": {
                tags: ["Favorite"],
                summary: "add the show to favorites",
                description: "Add the show to favorites",
                security: [{
                    bearerAuth: []
                }, ],
                parameters: [{
                    name: "showId",
                    in: "query",
                    description: "Le id du show",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "object",
                                    properties: {
                                        success: {
                                            type: "boolean",
                                            example: "true"
                                        }
                                    }
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
            "delete": {
                tags: ["Favorite"],
                summary: "delete the show from favorites",
                description: "Delete the show from favorites",
                security: [{
                    bearerAuth: []
                }, ],
                parameters: [{
                    name: "showId",
                    in: "query",
                    description: "Le id du show",
                    required: true,
                    type: "int",
                    example: 4
                }],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "object",
                                    properties: {
                                        success: {
                                            type: "boolean",
                                            example: "true"
                                        }
                                    }
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
        "/favorites": {
            "get": {
                tags: ["Favorite"],
                summary: "Get favorites of a user",
                description: "Get the favorites of a user",
                security: [{
                    bearerAuth: []
                }, ],
                responses: {
                    "200": {
                        description: "successful operation",
                        content: {
                            "application/json": {
                                schema: {
                                    type: "array",
                                    items: {
                                        $ref: "#/components/schemas/Favorite"
                                    },
                                }
                            }
                        }
                    },
                    "401": {
                        description: "Unauthorized",
                        content: {
                            "application/json": {
                                schema: {
                                    $ref: "#/components/schemas/401"
                                }
                            }
                        }
                    },
                    "500": {
                        description: "Internal Server Error"
                    }
                }
            },
        },
    },
    components: {
        securitySchemes: {
            bearerAuth: {
                type: "http",
                scheme: "bearer",
                bearerFormat: "JWT"
            }
        },
        schemas: {
            EpisodeDetail: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 3
                    },
                    seasonId: {
                        type: "integer",
                        example: 1
                    },
                    showId: {
                        type: "integer",
                        example: 1
                    },
                    episodeNumber: {
                        type: "integer",
                        example: 1
                    },
                    title: {
                        type: "string",
                        example: "I'm Luffy! The Man Who Will Become the Pirate King!"
                    },
                    length: {
                        type: "int",
                        example: "20"
                    },
                    imageURL: {
                        type: "string",
                        example: "https://static.wikia.nocookie.net/onepiece/images/a/a1/Episode_1.png/revision/latest/scale-to-width-down/350?cb=20140915112845"
                    },
                    videoURL: {
                        type: "string",
                        example: "https://www.google.com"
                    },
                }
            },
            EpisodeShort: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 3
                    },
                    showId: {
                        type: "integer",
                        example: 1
                    },
                    episodeNumber: {
                        type: "integer",
                        example: 1
                    },
                    title: {
                        type: "string",
                        example: "I'm Luffy! The Man Who Will Become the Pirate King!"
                    },
                    length: {
                        type: "int",
                        example: "20"
                    },
                    imageURL: {
                        type: "string",
                        example: "https://static.wikia.nocookie.net/onepiece/images/a/a1/Episode_1.png/revision/latest/scale-to-width-down/350?cb=20140915112845"
                    },
                }
            },
            Season: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 1
                    },
                    showId: {
                        type: "integer",
                        example: 1
                    },
                    title: {
                        type: "string",
                        example: "East Blue"
                    },
                    description: {
                        type: "string",
                        example: "The East Blue Saga is the first saga of the One Piece series."
                    },
                    imageURL: {
                        type: "string",
                        example: "https://m.media-amazon.com/images/M/MV5BZDQwMTYzN2EtNmFlOS00OTI1LWE5YmEtOTIyNGQwZjNiY2ZlXkEyXkFqcGdeQXVyMTEyNTc4NTI1._V1_.jpg"
                    },
                    releaseDate: {
                        type: "string",
                        example: "1999"
                    }
                }
            },
            Show: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 1
                    },
                    title: {
                        type: "string",
                        example: "East Blue"
                    },
                    description: {
                        type: "string",
                        example: "A man wants to become the pirate king"
                    },
                    imageURL: {
                        type: "string",
                        example: "https://images.justwatch.com/poster/248497985/s718/one-piece.%7Bformat%7D"
                    },
                    releaseDate: {
                        type: "string",
                        example: "2002"
                    },
                    genre: {
                        type: "string",
                        example: "Action"
                    },
                    rating: {
                        type: "integer",
                        example: "4"
                    },
                }
            },
            Favorite: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 1
                    },
                    title: {
                        type: "string",
                        example: "East Blue"
                    },
                    imageURL: {
                        type: "string",
                        example: "https://images.justwatch.com/poster/248497985/s718/one-piece.%7Bformat%7D"
                    }
                }
            },
            User: {
                type: "object",
                properties: {
                    id: {
                        type: "integer",
                        example: 1
                    },
                    username: {
                        type: "string",
                        example: "tekam"
                    },
                    email: {
                        type: "string",
                        example: "tekam@gmail.com"
                    },
                    isAdmin: {
                        type: "integer",
                        example: 0
                    },
                }
            },
            Comment: {
                type: "object",
                properties: {
                    commentId: {
                        type: "integer",
                        example: 1
                    },
                    episodeId: {
                        type: "integer",
                        example: 1
                    },
                    userId: {
                        type: "integer",
                        example: 1
                    },
                    content: {
                        type: "string",
                        example: "J'ai adoré cet épisode!"
                    },
                }
            },
            History: {
                type: "object",
                properties: {
                    historyId: {
                        type: "integer",
                        example: 1
                    },
                    episodeId: {
                        type: "integer",
                        example: 1
                    },
                    time: {
                        type: "string",
                        example: "2021-05-05 12:00:00"
                    },
                }
            },
            LoginResponse: {
                type: "object",
                properties: {
                    succes: {
                        type: "boolean",
                        example: "true"
                    },
                    message: {
                        type: "string",
                        example: "Vous êtes connecté"
                    },
                    token: {
                        type: "string",
                        example: "ABCTOKEN"
                    },
                    username: {
                        type: "string",
                        example: "Drunkudan"
                    },
                }
            },
            200: {
                type: "object",
                properties: {
                    succes: {
                        type: "boolean",
                        example: "true"
                    },
                    message: {
                        type: "string",
                        example: "Message Succes!"
                    },
                }
            },
            401: {
                type: "object",
                properties: {
                    succes: {
                        type: "boolean",
                        example: "false"
                    },
                    message: {
                        type: "string",
                        example: "Vous n'etes pas connecter!"
                    },
                }
            },
            404: {
                type: "object",
                properties: {
                    succes: {
                        type: "boolean",
                        example: "false"
                    },
                    message: {
                        type: "string",
                        example: "Not found!"
                    },
                }
            },
        },
    }
};

module.exports = swaggerDocument;
module.exports = swaggerDocument;