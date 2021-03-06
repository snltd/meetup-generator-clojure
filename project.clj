(defproject meetup-generator "0.1.0-SNAPSHOT"
  :description "Stupid fatuous random string thing"
  :url "https://meetup.sysdef.xyz"
  :min-lein-version "2.0.0"
  :main "meetup-generator.handler"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.forward/yaml "1.0.9"]
                 [compojure "1.6.1"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-json "0.5.0"]
                 [environ "1.0.0"]
                 [ring/ring-devel "1.7.1"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler meetup-generator.handler/app}
  :profiles
  {:dev {:main meetup-generator.handler/-dev-main
         :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
         :dependencies [[javax.servlet/servlet-api "2.5"]
                        [pjstadig/humane-test-output "0.10.0"]
                        [com.jakemccrary/lein-test-refresh "0.24.1"]
                        [ring/ring-mock "0.3.2"]]}})
