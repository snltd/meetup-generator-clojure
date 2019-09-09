# meetup-generator

A Clojure port of my [meetup generator](https://meetup.sysdef.xyz).

It's the first "proper" thing I've written in Clojure, and I expect
I've got everything wrong.

## Prerequisites

A working Clojure environment, and
[Leiningen](https://github.com/technomancy/leiningen) 2.0 or above.

## Running

To start in dev mode, with automatic reloading of changed code:

```
$ lein with-profile dev run
```

and to start a production server:

```
$ lein run
```

will start the server on port 8080.

## Deployment

The code will run on [Heroku](https://dashboard.heroku.com/) as-is.
