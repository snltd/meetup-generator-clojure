(defproject meetup-generator "0.1.0-SNAPSHOT"
  :description "Stupid fatuous random string thing"
  :url "https://meetup.sysdef.xyz"
  :min-lein-version "2.0.0"
  :main "meetup-generator.handler"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [io.forward/yaml "1.0.9"]
                 [compojure "1.6.1"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler meetup-generator.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
