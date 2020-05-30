#!/usr/bin/python
# -*- coding: utf-8 -*-

import datetime
import logging
import os
import random

from sqlalchemy.sql import text

import db
import settings
from db.models import SQLAlchemyBase, User, GenereEnum, UserToken, Question, Answer, \
    AnswerQuestionAssiation
from settings import DEFAULT_LANGUAGE

# LOGGING
mylogger = logging.getLogger(__name__)
settings.configure_logging()


def execute_sql_file(sql_file):
    sql_folder_path = os.path.join(os.path.dirname(__file__), "sql")
    sql_file_path = open(os.path.join(sql_folder_path, sql_file), encoding="utf-8")
    sql_command = text(sql_file_path.read())
    db_session.execute(sql_command)
    db_session.commit()
    sql_file_path.close()


if __name__ == "__main__":
    settings.configure_logging()

    db_session = db.create_db_session()

    # -------------------- REMOVE AND CREATE TABLES --------------------
    mylogger.info("Removing database...")
    SQLAlchemyBase.metadata.drop_all(db.DB_ENGINE)
    mylogger.info("Creating database...")
    SQLAlchemyBase.metadata.create_all(db.DB_ENGINE)



    # -------------------- CREATE USERS --------------------
    mylogger.info("Creating default users...")

    # noinspection PyArgumentList
    user_1= User(
        created_at=datetime.datetime(2020, 1, 1, 0, 1, 1),
        username="usuari1",
        email="usuari1@gmail.com",
        name="usuari",
        surname="1",
        birthdate=datetime.datetime(1989, 1, 1),
        phone=627218031,
        genere=GenereEnum.male,
    )
    user_1.set_exam("\n\n\n\n\n")
    user_1.set_password("a1s2d3f4")
    user_1.tokens.append(UserToken(token="656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf"))
    db_session.add(user_1)
    db_session.commit()

    names1 = ["Aatrox", "Ahri", "Anivia", "Ani", "Charmander", "Tyrion", "Sheldon", "Jax", "Jack", "Luffy", "Ace"
             "Kaido", "BigMom", "Sabo", "Goku", "Naruto", "Sasuke", "Tsunade", "Ichigo", "Amazeratu", "Genki", "KameHouse", "Bulma","Pikachu", "Thunder", "Fire", "Pro", "Shinigami", "Demon", "Devil", "Angel"]

    accounts = 20
    index =0
    for p in range(accounts):
        name = random.choice(names1)
        surname = random.choice(names1)
        username= name + surname + str(index)
        email = username.lower() +"@gmail.com"
        account = User(
            name=name,
            surname=surname,
            username=username,
            email=email,
            genere=random.choice(list(GenereEnum)),
            points = int(random.uniform(1, 1000)),
            birthdate=datetime.datetime(1989, 1, 1),
            phone=627218031
        )
        account.set_exam("\n\n\n\n\n")
        account.set_password("0000")
        db_session.add(account)
        index+=1
    db_session.commit()

    # -------------------- CREATE QUESTIONS --------------------
    mylogger.info("Creating default questions from file...")
    filepath = '/app/dev/questions'
    with open(filepath) as fp:
        line = fp.readline()
        cnt = 1
        q = None

        while line:
            l = line.split(":")
            if l[0]=="C":
                q = Question()
                q.category=str.strip(l[1])
            elif l[0]=="Q":
                q.question=str.strip(l[1])
            elif l[0] == "AF" or l[0] == "AT":
                q.owner_id = int(random.uniform(1, 20))
                db_session.add(q)
                db_session.commit()
                a = Answer()
                a.answer = str.strip(l[1])
                db_session.add(a)
                db_session.commit()

                s = AnswerQuestionAssiation()
                s.id_question = q.id
                s.id_answer = a.id
                if l[0] == "AT":
                    s.is_correct = True
                else:
                    s.is_correct = False
                db_session.add(s)
                db_session.commit()


            cnt += 1
            line = fp.readline()

    db_session.close()

  # -------------------- CREATE STUDENTS --------------------
    mylogger.info("Creating students and exams...")
    execute_sql_file("init.sql")
    db_session.commit()