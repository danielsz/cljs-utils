(ns cljs-utils.localstorage
  (:require [clojure.walk :refer [keywordize-keys]]))

(defn set-item!
  "Set `key' in browser's localStorage to `val`."
  [key val]
  (.setItem (.-localStorage js/window) key val))

(defn get-item
  "Returns value of `key' from browser's localStorage."
  ([key]
   (.getItem (.-localStorage js/window) key))
  ([key not-found]
   (or (get-item key) not-found)))

(defn remove-item!
  "Remove the browser's localStorage value for the given `key`"
  [key]
  (.removeItem (.-localStorage js/window) key))

(defn set-json-item!
  "Set `key' in browser's localStorage to `val`."
  [key val]
  (let [item (.stringify js/JSON (clj->js val))]
    (.setItem (.-localStorage js/window) key item)))

(defn get-json-item!
  "Returns value of `key' from browser's localStorage."
  [key]
  (let [item (.getItem (.-localStorage js/window) key)]
    (keywordize-keys (js->clj (.parse js/JSON item)))))

