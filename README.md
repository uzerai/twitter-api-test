--=FOREWORD:=--
I've included the .jar under ./api/target as I assume the one who will be running this may not have Maven installed...
(along with all other mvn compiled resources) -- if you want to build it yourself; run mvn clean package under /api.

I removed node_modules so it isn't included in the .zip -- it will install upon start.

NB: No error handling on the front-end; although it does contain signals for memory handling of requests.

--=TO START APPLICATION:=--
You'll need to supply Twitter developer credentials under
  ./docker-compose.yml -> services -> twitter-api-micronaut -> environment:
        - TWITTER_CONSUMER_KEY=
        - TWITTER_CONSUMER_SECRET=
        - TWITTER_ACCESS_TOKEN=
        - TWITTER_ACCESS_TOKEN_SECRET=

after that just run docker-compose up --build from ./
