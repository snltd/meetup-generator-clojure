(ns meetup-generator.lib
  (:require [yaml.core :as yaml]
            [clojure.string :as str]))

(def things (yaml/from-file "resources/all_the_things.yaml"))
(def words (str/split-lines (slurp "resources/words")))

(defn title
  "make a meetup talk title"
  ([]
    (fill-template (rand-nth (:template things))))
  ([template]
    (fill-template template)))

(defn talk
  "generate a talk with all the trimmings"
  ([] (talk (rand-nth (:template things))))
  ([template]
  { :title   (title template)
    :talker  (talker)
    :role    (role)
    :company (company) }))

(defn agenda
  "generate a full meetup agenda"
  ([] (agenda 5))
  ([how-many]
  { :talks       (vec (map talk (take how-many (shuffle (:template things)))))
    :refreshment (refreshment) }
  ))

(defn talker
  "the name of the person delivering the talk"
  []
  (pair :first_name :last_name))

(defn role
  "the pretentious title someone gives themself"
  []
  (pair :job_role :job_title))

(defn refreshment
  "what is served at the interval"
  []
  (pair :food_style :food))

(defn company
  "by whom a talker is employed"
  []
  (format "%s.io"
          (str/replace (rand-nth words) #"([^aeiou])er$" "$1r")))

(defn- something-ops
  "some new nonsense ..Ops thing"
  [_]
  (format "%sOps"
          (str/join
            (take (+ 2 (rand-int 3))
                  (repeatedly #(rand-nth (:something_ops things)))))))

(defn- random-number
  "return a string of a number between 2 and the given ceiling (inclusive)"
  [ceil]
  (str (+ 2 (rand-int (- ceil 1)))))

(defn- replace-things
  "replace %placeholders% in a template string"
  [template]
  (str/replace template
               #"%\w+%"
               #(rand-nth ((keyword (str/replace % "%" "")) things))))

(defn- replace-ops
  "replace FNOPS with a something-ops"
  [template]
  (str/replace template #"FNOPS" #(something-ops %)))

(defn- replace-number
  "replace RANDX with a number in a template string"
  [template]
  (str/replace template
               #"RAND(\d+)"
               #(random-number (read-string (peek %)))))

(defn- fill-template
  "turn a template into a title"
  [template]
  (replace-ops (replace-number (replace-things template))))

(defn- pair
  "pick two keys from the 'things' map and return them as a string"
  [first last]
  (format "%s %s" (rand-nth (first things)) (rand-nth (last things))))
