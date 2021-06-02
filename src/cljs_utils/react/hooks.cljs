(ns cljs-utils.react.hooks)

(defn useLens
  [a f]
  (let [[value update-value] (js/React.useState (f @a))]
    (js/React.useEffect
     (fn []
       (let [k (gensym "useLens")]
         (add-watch a k
                    (fn [_ _ _ new-state]
                      (update-value (f new-state))))
         (fn []
           (remove-watch a k)))))
    value))

(defn useWindowSize []
  ;; Initialize state with undefined width/height so server and client renders match
  ;; Learn more here: https://joshwcomeau.com/react/the-perils-of-rehydration/
  (let [[windowSize setWindowSize] (js/React.useState {:width nil :height nil})
        handle-resize #(setWindowSize {:width (.-innerWidth js/window) :height (.-innerHeight js/window)})]
    (js/React.useEffect (fn []
                          (.addEventListener js/window "resize" handle-resize) ;; add event listener
                          (handle-resize);;  Call handler right away so state gets updated with initial window size
                          #(.removeEventListener js/window "resize" handle-resize))
                        #js [])
    windowSize))
