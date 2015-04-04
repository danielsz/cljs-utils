(ns cljs-utils.core
  (:require [clojure.java.io :refer [file]]))

(defmacro read-file [uri]
  (slurp uri))

(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))

;; (map #(println (.getPath %)) (walk "src" #".*\.clj"))
(defmacro glob [root glob]
  (map #(.getPath %) (walk root glob)))

