(ns adventofcode2019.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(defn to-int [x]
  (Integer. x))


(def xs (map to-int (str/split (slurp "resources/day1.txt") #"\n")))

(defn round-down [d]
  (Math/round (Math/floor d)))

(defn calculate-fuel [i]
  (let [x (round-down (/ i 3))]
    (- x 2)))


(defn day1 []
  (reduce + (map calculate-fuel xs)))


