(ns cljs-utils.dev
  (:require [devtools.core :as devtools]))

(enable-console-print!)

(devtools/set-pref! :fn-symbol "λ")
(devtools/set-pref! :print-config-overrides true)
(devtools/install! [:formatters :hints])
