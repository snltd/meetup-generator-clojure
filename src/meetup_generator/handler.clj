(ns meetup-generator.handler
  (:require [meetup-generator.lib :as lib]
            [meetup-generator.template :as template]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (template/render (lib/agenda 5)))
  (route/not-found "Not Found"))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      (wrap-resource "public")
      (wrap-reload)))
