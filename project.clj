(defproject io.scurry/deadlib "0.0.1-SNAPSHOT"
  :description "A Clojure library for the Valve game Deadlock"
  :url "https://github.com/SethCurry/deadlib"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http/clj-http "3.13.1"]
                 [cheshire/cheshire "6.1.0"]]
  :repl-options {:init-ns deadlib.core})
