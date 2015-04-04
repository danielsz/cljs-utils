(ns cljs-utils.core
  (:require [clojure.java.io :refer [file]]
            [clojure.edn :refer [read-string]]))

(comment (defmacro read-file [uri]
           (slurp uri)))

(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))

;; (map #(println (.getPath %)) (walk "src" #".*\.clj"))
(defmacro slurp-edn [root glob]
  (mapv #(read-string (slurp (.getPath %))) (walk root glob)))

