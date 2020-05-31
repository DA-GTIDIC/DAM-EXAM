#!/usr/bin/python
# -*- coding: utf-8 -*-

SchemaUserToken = {
    "type": "object",
    "properties": {
        "token": {"type": "string"},
    },
    "required": ["token"]
}

SchemaRegisterUser = {
    "type": "object",
    "properties": {
        "username": {"type": "string"},
        "password": {"type": "string"},
        "email": {"type": "string"},
        "name": {"type": "string"},
        "surname": {"type": "string"},
        "genere": {"type": "string"}
    },
    "required": ["username", "password", "email", "name", "surname", "genere"]
}

SchemaUpdateRate = {
    "type": "object",
    "properties": {
        "id": {"type": "number"},
        "question": {"type": "string"},
        "answer": {"type": "string"},
        "rate": {"type": "number"},
    },
    "required": []
}
