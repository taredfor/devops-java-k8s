В Docker Compose передавать для подключения:

`CATALINA_OPTS=-DJDBC_DATABASE_URL="jdbc:postgresql://db:5432/db?user=app&password=pass"`

Для проверки функциональности сделать следующий запрос:

`curl -H "Authorization: 6NSb+2kcdKF44ut4iBu+dm6YLu6pakWapvxHtxqaPgMr5iRhox/HlhBerAZMILPjwnRtXms+zDfVTLCsao9nuw==" http://localhost:8080/cards.getAll`

В ответ должно прийти:

`[{"id":1,"number":"**** *888","balance":50000}]`
