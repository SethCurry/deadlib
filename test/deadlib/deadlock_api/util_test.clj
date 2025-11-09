(ns deadlib.deadlock-api.util-test
  (:require [clojure.test :as t]
            [deadlib.deadlock-api.util :refer [unmarshal-map]]))


(t/deftest unmarshal-map-test
  (t/testing "simple test"
    (let [data {:a 1 :b 2 :c 3}
          unmarshallers {:a {:unmarshal-fn (fn [x] {:a x})}
                         :b {:unmarshal-fn (fn [x] {:b x})}
                         :c {:unmarshal-fn (fn [x] {:c x})}}]
      (t/is (let [result (unmarshal-map data unmarshallers)]
              (= result {:a 1 :b 2 :c 3})))))
  (t/testing "optional test"
    (let [optional-data {:a 1 :b 2}
          optional-unmarshallers {:a {:unmarshal-fn (fn [x] {:a x})}
                                  :b {:unmarshal-fn (fn [x] {:b x})}
                                  :c {:unmarshal-fn (fn [x] {:c x}) :optional? true}}
          option-result (unmarshal-map optional-data optional-unmarshallers)]
      (t/is (= option-result {:a 1 :b 2}))))
  (t/testing "extra keys test"
    (let [data {:a 1 :b 2 :c 3}
          unmarshallers {:a {:unmarshal-fn (fn [x] {:a x})}
                         :c {:unmarshal-fn (fn [x] {:c x})}}]
      (t/is (thrown-with-msg? Exception #"Data has extra keys: :b" (unmarshal-map data unmarshallers))))))