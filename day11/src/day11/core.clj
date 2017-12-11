(ns day11.core
  (:use [clojure.string :as str]))

(def text (slurp "input.txt"))

(defn direction-to-coords [direction]
  (case direction
    :north   [0 1 -1]
    :northeast [1 0 -1]
    :southeast [1 -1 0]
    :south [0 -1 1]
    :southwest [-1 0 1]
    :northwest [-1 1 0]))

(defn string-to-direction [input-str]
  (case (trim input-str)
    "n" :north
    "ne" :northeast
    "se" :southeast
    "s" :south
    "sw" :southwest
    "nw" :northwest))

(defn parse-input [text]
  (map string-to-direction (str/split text #",")))


(defn steps-to-relative-coords [steps]
  (let [vectors (map direction-to-coords steps)]
    (reduce (fn [s n] (mapv + s n)) vectors)))

(defn list-of-positions [steps]
  (let [vectors (map direction-to-coords steps)]
    (reduce (fn [s n] (conj s (mapv + (last s) n))) [[0 0 0]] vectors)))

(defn distance [rel-coords]
  (reduce max (mapv #(Math/abs (- %1 %2)) rel-coords [0 0 0])))

(defn max-distance [positions]
  (reduce (fn [s n] (max (distance n) s)) 0 positions))

(defn -main []
  (let [steps (parse-input text)
        rel-coords (steps-to-relative-coords steps)
        list-of-positions (list-of-positions steps)
        max-distance (max-distance list-of-positions)]
    (println (distance rel-coords))
    (println max-distance)))
