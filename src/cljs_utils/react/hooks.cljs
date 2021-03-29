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
