(ns cljs-utils.core
  (:require [goog.events :as events]
            [cljs.core.async :as async :refer [<! >! put! take! chan]]))

(defn dev-mode? [port]
  "Return boolean if development mode, derived from non-standard port in window.location"
  (= js/window.location.port (str port)))

(defn by-id
  "Short-hand for document.getElementById(id)"
  [id]
  (.getElementById js/document id))

(defn listen [el type]
  "listen on js events in a channel and return it"
  (let [out (chan)]
    (events/listen el type
      (fn [e] (put! out e)))
    out))

(defn set-interval
  "Invoke the given function after and every delay milliseconds."
  [delay f]
  (js/setInterval f delay))

(defn clear-interval
  "Cancel the periodic invokation specified by the given interval id."
  [interval-id]
  (js/clearInterval interval-id))


(defn events->chan
  "Given a target DOM element and event type return a channel of
  observed events. Can supply the channel to receive events as third
  optional argument."
  ([el event-type] (events->chan el event-type (chan)))
  ([el event-type c]
   (events/listen el event-type
     (fn [e] (put! c e)))
   c))

(defn mouse-loc->vec
  "Given a Google Closure normalized DOM mouse event return the
  mouse x and y position as a two element vector."
  [e]
  [(.-clientX e) (.-clientY e)])

(defn show!
  "Given a CSS id and a message string append a child paragraph element
  with the given message string."
  [id msg]
  (let [el (.getElementById js/document id)
        p  (.createElement js/document "p")]
    (set! (.-innerHTML p) msg)
    (.appendChild el p)))
