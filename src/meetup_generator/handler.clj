(ns meetup-generator.handler
  (:require [meetup-generator.lib :as lib]
            [meetup-generator.template :as template]
            [meetup-generator.api :as api]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :as resp]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/api/:word" [word]
       (resp/content-type
         (resp/response (api/handler word))
        "application/json"))

  (GET "*" [] (template/render (lib/agenda 5))))

(def app
  (wrap-resource
    (wrap-json-response (wrap-defaults app-routes site-defaults))
      "public"))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 8080))]
  (jetty/run-jetty #'app {:port port :join? false})))

(defn -dev-main [& [port]]
  (let [port (Integer. (or port (env :port) 8888))]
  (jetty/run-jetty (wrap-reload #'app) {:port port :join? false})))
