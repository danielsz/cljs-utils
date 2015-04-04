(ns cljs-utils.core)

(defmacro read-file [uri]
  (slurp uri))
