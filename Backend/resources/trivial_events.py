import falcon
from falcon.media.validators import jsonschema
from pymysql import IntegrityError
from sqlalchemy import func
from sqlalchemy.orm.exc import NoResultFound

import messages
from db.models import CategoryEnum, Question, Answer, AnswerQuestionAssiation
from hooks import requires_auth
from resources.base_resources import DAMCoreResource
from resources.schemas import SchemaUpdateRate


@falcon.before(requires_auth)
class ResourceGetQuestions(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetQuestions, self).on_get(req, resp, *args, **kwargs)

        questions = list()
        query = self.db_session.query(Question)

        for user in query.all():
            questions.append(user.json_model)

        resp.media = questions
        resp.status = falcon.HTTP_200

@falcon.before(requires_auth)
class ResourceGetRandomQuestion(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourceGetRandomQuestion, self).on_get(req, resp, *args, **kwargs)

        category_filter = req.get_param("category", False)
        if category_filter is not None:
            if category_filter not in [i.value for i in CategoryEnum.__members__.values()]:
                raise falcon.HTTPInvalidParam(messages.event_status_invalid, "category")

        query = self.db_session.query(Question).filter().order_by(func.rand())
        if category_filter is not None:
            query = query.filter(Question.category == CategoryEnum(category_filter))

        question = query.first()
        print(question.json_model)

        query = self.db_session.query(Answer, AnswerQuestionAssiation.is_correct) \
            .join(AnswerQuestionAssiation).filter(question.id == AnswerQuestionAssiation.id_question)

        answers = query.all()
        response = question.json_model
        response["answers"] = []

        for a in answers:
            aux = a[0].json_model
            aux["is_correct"] = a[1]
            response["answers"].append(aux)

        resp.media = response
        resp.status = falcon.HTTP_200


@falcon.before(requires_auth)
class ResourceAddQuestion(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceAddQuestion, self).on_get(req, resp, *args, **kwargs)

        q = Question()

        try:
            try:
                q.question = req.media["question"]
                category = req.media["category"]

                if category not in [i.value for i in CategoryEnum.__members__.values()]:
                    raise falcon.HTTPInvalidParam(messages.event_status_invalid, "category")
                q.category = category
                answers = req.media["answers"]
                self.db_session.add(q)
                for answer in answers:
                    a = Answer()
                    a.answer = answer["answer"]
                    self.db_session.add(a)
                    self.db_session.commit()
                    s = AnswerQuestionAssiation()
                    s.id_question = q.id
                    s.id_answer = a.id

                    if answer["is_correct"]:
                        s.is_correct = True
                    else:
                        s.is_correct = False

                    self.db_session.add(s)
                    self.db_session.commit()

            except IntegrityError:
                raise falcon.HTTPBadRequest(description=messages.user_exists)

        except KeyError:
            raise falcon.HTTPBadRequest(description=messages.parameters_invalid)

        resp.status = falcon.HTTP_200



@falcon.before(requires_auth)
class UpdateRate(DAMCoreResource):
    @jsonschema.validate(SchemaUpdateRate)
    def on_post(self, req, resp, *args, **kwargs):
        super(UpdateRate, self).on_post(req, resp, *args, **kwargs)

        current_user = req.context["auth_user"]
        #Assegurar que el id del favor correspon al id del usuari
        if "id" in kwargs:
            question = self.db_session.query(Question).filter(Question.id == kwargs["id"]).one()
            try:


                if (req.media["rating"]) is not None:
                    question.rate = req.media["rating"]

                    print("RATE----------" + str(question.rate))

                self.db_session.add(question)
                self.db_session.commit()

                resp.status = falcon.HTTP_200

            except NoResultFound:
                raise falcon.HTTPBadRequest(description=messages.user_not_found)  # TODO
