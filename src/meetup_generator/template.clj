(ns meetup-generator.template
  (:use [hiccup.page :only (html5 include-css)]
        [hiccup.element :only (link-to)]))

(import (java.time LocalDate)
        (java.time.format DateTimeFormatter))

(def formatter (DateTimeFormatter/ofPattern "dd/MM/yyyy"))

(def tomorrow (.plusDays (LocalDate/now) 1))

(defn talk [start-time subtitle content offset]
  (def t (get (:talks content) offset))
  [:li start-time " // " subtitle
  [:span {:class "ttitle"} (:title t)]
  [:div {:class "tperson" } (:talker t) " // "
  (:role t) " @ " (:company t)]]
  )

(defn render [content]
  (html5 {:ng-app "meetup generator" :lang "en"}
         [:head
           [:title "#DevOps Meetup"]
           (include-css "/css/main.css")
         ]
         [:body
          [:div {:id "container" }
           [:h1 "#DevOps Meetup // Shoreditch, probably // " (.format formatter tomorrow) ]
           [:ul
            [:li "18:00 // Introduction"]
            (talk "18:10" "Lightning Talk //" content 0)
            (talk "18:20" nil content 1)
            [:li "18:50 // break"
              [:div {:class "tperson"} (:refreshment content)]]
            (talk "19:20" nil content 2)
            (talk "19:40" "Ignite //" content 3)
            (talk "20:00" nil content 4)
            [:li "20:30 // Close"
             [:div {:class "tperson"}
              "Everyone is hiring, but no one's paying"]]
           ] ; ul
           [:div {:id "footer"}
            (link-to "https://github.com/snltd/meetup-generator-clojure"
                     "The code")]

          ]
         ]; closebody
    ))
