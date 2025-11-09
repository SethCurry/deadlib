(ns deadlib.deadlock-api.client-test
  (:require [clojure.test :as t]
            [deadlib.deadlock-api.client :refer [get-hero]]))

(t/deftest get-hero-test
  (t/testing "get-hero should return a hero"
    (let [hero (get-hero 1)]
      (t/is (not (nil? hero)))
      (t/is (= 1 (:id hero)))
      (t/is (= "Infernus" (:name hero))))))
