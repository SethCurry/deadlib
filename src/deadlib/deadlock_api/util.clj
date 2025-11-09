(ns deadlib.deadlock-api.util
  (:require [clojure.data :refer [diff]]
            [clojure.string :as str]))

;{:a-key {:fn () :optional? true}}

(defn unmarshal-map
  "Unmarshals a map using a map of unmarshallers.
   
   Unmarshallers are expected to return a map that will be merged into the result."
  [as-map unmarshallers]
  (let [diff-result (diff (set (keys as-map)) (set (keys unmarshallers)))]
    (when-not (nil? (first diff-result))
      (throw (Exception. (str "Data has extra keys: " (str/join ", " (first diff-result)))))))
  (let [unmarshal-items (partition 2 (reduce-kv conj [] unmarshallers))]
    (reduce (fn [acc unmarshal-item]
              (let [key (first unmarshal-item)
                    unmarshal-config (second unmarshal-item)
                    unmarshal-fn (:unmarshal-fn unmarshal-config)
                    optional-base (:optional? unmarshal-config)
                    optional? (if (nil? optional-base) false optional-base)]
                (if (contains? as-map key)
                  (conj acc (unmarshal-fn (get as-map key)))
                  (if optional?
                    acc
                    (throw (Exception. (str "Key " key " not found in JSON")))))))
            {}
            unmarshal-items)))