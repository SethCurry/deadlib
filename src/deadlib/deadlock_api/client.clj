(ns deadlib.deadlock-api.client
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [deadlib.deadlock-api.heroes :refer [hero-from-map]]))

; Docs at https://assets.deadlock-api.com/scalar#tag/heroes/get/v2/heroes
(defn list-heroes []
  (let [response (http/get "https://assets.deadlock-api.com/v2/heroes")]
    (json/parse-string (:body response) true)))

(defn get-hero [id]
  (let [response (http/get (str "https://assets.deadlock-api.com/v2/heroes/" id))]
    (hero-from-map (json/parse-string (:body response) true))))

(defn list-items []
  (let [response (http/get "https://assets.deadlock-api.com/v2/items")]
    (json/parse-string (:body response) true)))
