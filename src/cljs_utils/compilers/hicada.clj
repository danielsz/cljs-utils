(ns cljs-utils.compilers.hicada
  (:require [hicada.compiler :refer [compile]]))

(defmacro html
  [body]
  (compile body {:create-element 'js/React.createElement
                                 :transform-fn (comp)
                                 :array-children? false}
                                 {} &env))

