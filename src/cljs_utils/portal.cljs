(ns cljs-utils.portal
  (:require [portal.web :as p]
            [portal.runtime.web.client :refer [Portal]]))

(def portal (atom {}))

(defn toggle []
  (if (instance? Portal @portal)
    (do (p/close)
        (remove-tap #'p/submit)
        (reset! portal {}))
    (do
      (reset! portal (p/open {:portal.colors/theme :portal.colors/solarized-light}))
      (add-tap #'p/submit))))
