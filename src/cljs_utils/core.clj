(ns cljs-utils.core
  (:require [clojure.java.io :refer [file]]
            [clojure.edn :as edn]))

(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))

(defn slurp-glob [root glob]
  (mapv #(edn/read-string (slurp (.getPath %))) (walk root glob)))

(defmacro slurp-edn [root]
  (slurp-glob root #".*\.edn"))
