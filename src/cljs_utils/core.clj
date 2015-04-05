(ns cljs-utils.core
  (:require [clojure.java.io :refer [file]]
            [clojure.edn :as edn]))

(comment (defmacro read-file [uri]
           (slurp uri)))

(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))

;; usage: (slurp-glob "resources/stations" #".*\.edn"))
(defn slurp-glob [root glob]
  (mapv #(edn/read-string (slurp (.getPath %))) (walk root glob)))

(defmacro slurp-edn [root]
  (slurp-glob root #".*\.edn"))

