(ns cljs-utils.core
  (:require [clojure.java.io :refer [file]]
            [clojure.edn :refer [read-string]]))

(comment (defmacro read-file [uri]
           (slurp uri)))

(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))

;; usage: (slurp-glob "resources/stations" #".*\.edn"))
(defn slurp-glob [root glob]
  (mapv #(read-string (slurp (.getPath %))) (walk root glob)))

(defmacro slurp-edn [root]
  (mapv #(read-string (slurp (.getPath %))) (walk root #".*\.edn")))

