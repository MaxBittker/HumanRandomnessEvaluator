(ns hrand.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [clojure.string :as str]
    [hrand.rand :as hrand]))


(enable-console-print!)

(println "This text is printed from src/hrand/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state 
  (atom {:human []
          :cpu []}))

(defn input [x]
  (fn [] 
    (swap! app-state update-in [:human] #(conj % x))
    (swap! app-state update-in [:cpu] #(conj % (rand-int 2)))))
  

(defn hello-world []
  [:div
   [:h4 (apply str (:human @app-state))]
   [:h1 (hrand/R (:human @app-state))]
   [:h4 (apply str (:cpu @app-state))]
   [:h1 (hrand/R (:cpu @app-state))]
   [:button {:onClick (input 0)} "A"]
   [:button {:onClick (input 1)}"B"]])

(reagent/render-component [hello-world]
  (. js/document (getElementById "app")))


(.addEventListener js/window "keypress" 
 (fn [e]
  (let [k (.-key e)]
    (cond 
      (= "a" k) ((input 0))
      (= "b" k) ((input 1)))))) 

;; optionally touch your app-state to force rerendering depending on
(defn on-js-reload [])
  ; (.removeEventListener js/window "keypress")
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

