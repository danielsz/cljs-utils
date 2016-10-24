(ns cljs-utils.sessionstorage
  (:require [clojure.walk :refer [keywordize-keys]]))

(defn set-item!
  "Set `key' in browser's sessionStorage to `val`."
  [key val]
  (.setItem (.-sessionStorage js/window) key val))

(defn get-item
  "Returns value of `key' from browser's sessionStorage."
  [key]
  (.getItem (.-sessionStorage js/window) key))

(defn remove-item!
  "Remove the browser's sessionStorage value for the given `key`"
  [key]
  (.removeItem (.-sessionStorage js/window) key))

(defn set-json-item!
  "Set `key' in browser's sessionStorage to `val`."
  [key val]
  (let [item (.stringify js/JSON (clj->js val))]
    (.setItem (.-sessionStorage js/window) key item)))

(defn get-json-item!
  "Returns value of `key' from browser's sessionStorage."
  [key]
  (let [item (.getItem (.-sessionStorage js/window) key)]
    (keywordize-keys (js->clj (.parse js/JSON item)))))

