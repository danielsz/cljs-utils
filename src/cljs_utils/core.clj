(ns cljs-utils.core
  (:require [clojure.edn]))

(defmacro read-file [uri]
  (slurp uri))

(defmacro read-edn [string]
  (clojure.edn/read-string string))
