(ns meetup-generator.api)

(def api-paths #{"talker" "role" "refreshment" "company"
                 "title" "talk" "agenda"})

(defn merp []
  "MERP MERP" )

(defn handler [word]
    (if (contains? api-paths word)
      {(keyword word) ((resolve (symbol "meetup-generator.lib" word))) }
      {:error "unsupported API path"})
)

