(ns hrand.core
  (:gen-class)
  (require '[clojure.string :as str]))

(def d (take 10000 (repeatedly (partial rand-int 2))))

(def human (str/split "1000100010101010100101001010100010101010101010010111011010100010101110101101010100010111010011010101010010101001011101010" #""))

(defn log2 [n] (/ (Math/log n) (Math/log 2)))

(defn tuple-frequencies
  [input n]
  (vals (frequencies (partition n 1 input))))
  
(defn Hmax [l]
  (log2 (Math/pow 2 l)))
  
(defn Hsingle [input]
  (- (log2 (count input))
    (/
      (apply + 
        (map 
          (fn [n] (* n (log2 n)))
          (tuple-frequencies input 2)))
      (dec (count input)))))

(defn R [input]
  (* 100
    (- 1
      (/ (Hsingle input) (Hmax 2)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (R d)))


