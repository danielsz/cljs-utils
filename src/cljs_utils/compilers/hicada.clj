(ns cljs-utils.compilers.hicada
  (:refer-clojure :exclude [compile])
  (:require [hicada.compiler :refer [compile]]))

(defmacro html
  [body]
  (compile body {:create-element 'js/React.createElement
                                 :transform-fn (comp)
                                 :array-children? false}
                                 {} &env))

