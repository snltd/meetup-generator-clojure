(ns meetup-generator.handler
  (:require [meetup-generator.lib :as lib]
            [meetup-generator.template :as template]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (template/render (lib/agenda 5)))
  (route/not-found "404. Sorry."))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      (wrap-resource "public")))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 8080))]
  (jetty/run-jetty #'app {:port port :join? false})))
