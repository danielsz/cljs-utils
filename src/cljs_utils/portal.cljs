(ns cljs-utils.portal
  (:require [portal.web :as p]))

(def portal (atom {}))

(defn toggle []
  (if (seq @portal)
    (do (p/close)
        (remove-tap #'p/submit)
        (reset! portal {}))
    (do
      (reset! portal (p/open {:portal.colors/theme :portal.colors/solarized-light}))
      (add-tap #'p/submit))))
