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



(defn better-calculate-fuel [i]
  (let [x (calculate-fuel i)]
    (if (<= x 0)
      0
      (+ x (better-calculate-fuel x)))))

(defn better-calculate-fuel-recursion
  ([acc i]
   (let [x (calculate-fuel i)]
     (if (<= x 0)
       acc
       (recur (+ acc x) x))))
  ([i]
   (better-calculate-fuel-recursion 0 i)))


(def ys (map to-int (str/split (slurp "resources/day1.1.txt") #"\n")))

(defn day1.1 []
    (reduce + (map better-calculate-fuel-recursion ys)))


